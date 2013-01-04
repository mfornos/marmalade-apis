package marmalade.apis.twitter.api;

import marmalade.Marmalade;
import marmalade.client.async.DefaultAsyncClient;
import marmalade.oauth.creds.ConfigCredentialsProvider;
import marmalade.oauth.creds.ConsumerCredentialsProvider;
import marmalade.oauth.spi.OAuthConfig;
import marmalade.oauth.spi.OAuthCredentialsProvider;
import marmalade.oauth.util.OAuthClientHelper;
import oauth.signpost.OAuthConsumer;

public class PreemptiveTwitterClient extends DefaultAsyncClient implements TwitterClient
{
   private String key;
   private String secret;

   public PreemptiveTwitterClient()
   {
      this(new TwitterConfig());
   }

   public PreemptiveTwitterClient(OAuthConfig config)
   {
      this(new ConfigCredentialsProvider(config));
   }

   public PreemptiveTwitterClient(final OAuthConsumer consumer)
   {
      this(new ConsumerCredentialsProvider(consumer));
   }

   public PreemptiveTwitterClient(OAuthCredentialsProvider provider)
   {
      enableAuth(provider);
   }

   @Override
   public String getKey()
   {
      return key;
   }

   @Override
   public String getSecret()
   {
      return secret;
   }

   @Override
   public boolean isPreemptive()
   {
      return true;
   }

   @Override
   public void setTokens(OAuthConsumer consumer)
   {
      ConsumerCredentialsProvider provider = new ConsumerCredentialsProvider(consumer);
      enableAuth(provider);
   }

   @Override
   public Marmalade signed(Marmalade m, Object context)
   {
      throw new UnsupportedOperationException();
   }

   protected void enableAuth(OAuthCredentialsProvider provider)
   {
      this.key = provider.getKey();
      this.secret = provider.getSecret();
      OAuthClientHelper.enablePreemptiveAuth(this, provider);
   }
}
