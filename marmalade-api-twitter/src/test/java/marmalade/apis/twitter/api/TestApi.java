package marmalade.apis.twitter.api;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import marmalade.Marmalade;
import marmalade.apis.twitter.api.geo.GeoSearch;
import marmalade.apis.twitter.api.geo.Place;
import marmalade.apis.twitter.api.geo.PlacesResult;
import marmalade.apis.twitter.api.tweets.Tweet;
import marmalade.client.Response;
import marmalade.oauth.util.ConsoleFlow;
import oauth.signpost.exception.OAuthException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestApi
{
   private TwitterApi api;
   private PreemptiveTwitterClient client;

   @BeforeClass
   public void beforeClass()
   {
      client = new PreemptiveTwitterClient();
      api = new TwitterApi(client);
   }

   @AfterClass
   public void afterClass()
   {
      api.shutdown();
   }

   // @Test
   public void flow() throws OAuthException, IOException
   {
      TwitterFlow flow = new TwitterFlow(new TwitterConfig());
      ConsoleFlow.execute(flow);
      // Access token: 1039123496-FU8z3bhhbWRRROOnhgFAIGzpMD6PW8oqQWiiCZN
      // Token secret: DzKSahHq9S0949iMy4FQXmZlCvQcEA9szLmgZ5NE
   }

  // @Test
   public void test() throws InterruptedException, ExecutionException
   {

      Tweet[] tweets = api.homeTimeline();

      for (Tweet tweet : tweets) {
         System.out.println(tweet);
      }

      // ///////
      // PlacesResult p =
      // api.searchPlaces(GeoSearch.searchBuilder("Barcelona"));
      //
      // // PlacesResult p =
      // //
      // TwitterApi.reverseGeocode(GeoSearch.searchBuilder().lat(37.76893497).lon(-122.42284884));
      // //
      // System.out.println(p);
      // Set<Place> places = p.getResult().getPlaces();
      // for (Place pl : places) {
      // System.out.println(pl);
      // }

      // Places p = TwitterApi.places("df51dec6f4ee2b2c");
      // System.out.println(p);

      // Rate limit headers
      // X-Access-Level: read, X-RateLimit-Limit: 350, X-RateLimit-Remaining:
      // 347, X-RateLimit-Reset: 1356601347, X-RateLimit-Class: api_identified

//       Future<Response> response =
//       Marmalade.Get("https://api.twitter.com/1.1/statuses/home_timeline.json").sendAsync(client);
//       System.out.println(response.get().asString());
   }
}
