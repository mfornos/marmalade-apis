package marmalade.apis.example.twitter;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import marmalade.apis.example.BaseResource;
import marmalade.apis.twitter.api.DefaultTwitterClient;
import marmalade.apis.twitter.api.TwitterApi;
import marmalade.apis.twitter.api.tweets.Tweet;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthException;

@Path("twitter")
public class TwitterResource extends BaseResource
{

   // Okay, only for sample purposes
   static TwitterApi api = new TwitterApi(new DefaultTwitterClient());

   @GET
   @Path("auth")
   public Response getAuth(@QueryParam("oauth_verifier") String verifier) throws ServletException, IOException,
         OAuthException, URISyntaxException
   {
      synchronized (api) {
         OAuthConsumer consumer = api.verify(verifier);
         // XXX Save tokens in a proper place, do it on Api impl.
         System.out.println("token.key=" + consumer.getToken());
         System.out.println("token.secret=" + consumer.getTokenSecret());
         
         return redirect("/apis/twitter/timeline");
      }
   }

   @GET
   @Path("timeline")
   public Response getTimeline() throws ServletException, IOException
   {
      Tweet[] tweets = api.homeTimeline();
      return forwardModel(tweets, "twitter/tweets");
   }
   
}
