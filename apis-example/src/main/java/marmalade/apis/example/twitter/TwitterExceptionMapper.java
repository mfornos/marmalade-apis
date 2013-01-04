package marmalade.apis.example.twitter;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import marmalade.apis.twitter.api.TwitterApiException;
import oauth.signpost.exception.OAuthException;

import org.apache.http.HttpStatus;

@Provider
public class TwitterExceptionMapper implements ExceptionMapper<TwitterApiException>
{

   // TODO to BaseOAuthCallbackMapper<? extends ResponseErrorException>... :/ localización de la api..!!?
   public Response toResponse(TwitterApiException exception)
   {

      if (exception.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {

         try {
            // TODO ContextPath
            String authUrl = TwitterResource.api.startFlow("http://127.0.0.1:8080/apis/twitter/auth");
            return Response.seeOther(new URI(authUrl)).build();
         } catch (OAuthException e) {
            // Check callback config Error
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }

      // XXX
      System.out.println("NOP" + exception.getCause());

      return Response.noContent().build();
   }

}
