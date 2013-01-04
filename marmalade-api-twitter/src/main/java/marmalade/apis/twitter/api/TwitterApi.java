package marmalade.apis.twitter.api;

import static marmalade.Marmalade.Get;
import static marmalade.client.UriBuilder.uriBuilderFrom;

import java.util.concurrent.Future;

import marmalade.Marmalade;
import marmalade.apis.twitter.api.geo.GeoSearch;
import marmalade.apis.twitter.api.geo.Place;
import marmalade.apis.twitter.api.geo.PlacesResult;
import marmalade.apis.twitter.api.tweets.Tweet;
import marmalade.client.UriBuilder;
import marmalade.spi.Registry;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthException;

import com.google.common.base.Preconditions;

public class TwitterApi
{
   private static final String BASE_URI = "https://api.twitter.com/1.1";
   private static final String GEO_SEARCH = "geo/search.json";
   private static final String GEO_REVERSE = "geo/reverse_geocode.json";
   private static final String GEO = "geo/id/";

   // TODO in BaseOAuthClient or BaseOAuthApi?
   private final TwitterClient client;
   private TwitterFlow flow;

   public TwitterApi()
   {
      this(Registry.lookup(TwitterClient.class));
   }

   public TwitterApi(TwitterClient client)
   {
      Preconditions.checkNotNull(client);

      this.client = client;
   }

   public void cancelFlow()
   {
      this.flow = null;
   }

   // TODO params
   public Tweet[] homeTimeline()
   {
      try {
         return promiseHomeTimeline().get();
      } catch (Exception e) {
         throw new TwitterApiException(e);
      }
   }

   public Place places(String id)
   {
      try {
         return promisePlaces(id).get();
      } catch (Exception e) {
         throw new TwitterApiException(e);
      }
   }

   public Future<Tweet[]> promiseHomeTimeline()
   {
      return defaults(Get(svcPath("/statuses/home_timeline.json").build())).mapAsync(client, Tweet[].class);
   }

   public Future<Place> promisePlaces(String id)
   {
      return defaults(Get(svcPath(GEO, id + ".json").build())).mapAsync(client, Place.class);
   }

   public Future<PlacesResult> promiseReverseGeocode(GeoSearch search)
   {
      Preconditions.checkNotNull(search.getLat(), "Latitude is required.");
      Preconditions.checkNotNull(search.getLon(), "Longitude is required.");

      return defaults(Get(search.buildUri(svcPath(GEO_REVERSE)))).mapAsync(client, PlacesResult.class);
   }

   public Future<PlacesResult> promiseSearchPlaces(GeoSearch search)
   {
      return defaults(Get(search.buildUri(svcPath(GEO_SEARCH)))).mapAsync(client, PlacesResult.class);
   }

   public PlacesResult reverseGeocode(GeoSearch search)
   {
      try {
         return promiseReverseGeocode(search).get();
      } catch (Exception e) {
         throw new TwitterApiException(e);
      }
   }

   public PlacesResult searchPlaces(GeoSearch search)
   {
      try {
         return promiseSearchPlaces(search).get();
      } catch (Exception e) {
         throw new TwitterApiException(e);
      }
   }

   public void shutdown()
   {
      cancelFlow();
      client.shutdown();
   }

   public String startFlow() throws OAuthException
   {
      return startFlow(OAuth.OUT_OF_BAND);
   }

   public String startFlow(String callback) throws OAuthException
   {
      try {
         this.flow = new TwitterFlow(client.getKey(), client.getSecret());
         return this.flow.requestAuthorization(callback);
      } catch (OAuthException e) {
         this.flow = null;
         throw e;
      }
   }

   public OAuthConsumer verify(String verifier) throws OAuthException
   {
      synchronized (flow) {
         Preconditions.checkNotNull(flow);

         try {
            OAuthConsumer consumer = flow.confirmAuthorization(verifier);
            client.setTokens(consumer);
            return consumer;
         } finally {
            flow = null;
         }
      }
   }

   protected Marmalade defaults(Marmalade m)
   {
      // XXX context
      return client.isPreemptive() ? m : client.signed(m, null);
   }

   protected UriBuilder svcPath(Object... paths)
   {
      return uriBuilderFrom(BASE_URI).appendPaths(paths);
   }

}
