package marmalade.apis.capsule.spi;

import marmalade.client.async.AsyncClient;
import marmalade.spi.Named;
import marmalade.spi.ServiceProvider;

@Named("cpsl")
public class CapsuleServiceProvider implements ServiceProvider<AsyncClient>
{

   @Override
   public Class<AsyncClient> serviceClass()
   {
      return AsyncClient.class;
   }

   @Override
   public AsyncClient serviceImpl()
   {
      return new CapsuleClient();
   }

}
