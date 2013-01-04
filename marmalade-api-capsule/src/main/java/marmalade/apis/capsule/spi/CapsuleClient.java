package marmalade.apis.capsule.spi;

import marmalade.client.async.DefaultAsyncClient;
import marmalade.client.config.DefaultPropertyConfig;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.params.ClientParamBean;
import org.apache.http.client.params.CookiePolicy;

public class CapsuleClient extends DefaultAsyncClient
{

   private final String capsuleHost;

   public CapsuleClient()
   {
      super();

      DefaultPropertyConfig config = new DefaultPropertyConfig("capsule.config", "capsule.properties");
      auth(new UsernamePasswordCredentials(config.get("token"), "x"));
      authPreemptive("basic");
      ClientParamBean cparams = new ClientParamBean(getHttpClient().getParams());
      cparams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
      this.capsuleHost = String.format("%s/api/", config.get("host"));
   }

   public String getCapsuleHost()
   {
      return capsuleHost;
   }

}
