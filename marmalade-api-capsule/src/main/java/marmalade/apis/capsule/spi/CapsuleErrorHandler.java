package marmalade.apis.capsule.spi;

import marmalade.client.Response;
import marmalade.client.handlers.ErrorHandler;

public class CapsuleErrorHandler implements ErrorHandler
{

   @Override
   public void onError(Response response)
   {
      throw new CapsuleException(response);
   }

}
