package marmalade.apis.capsule.spi;

import marmalade.client.Response;
import marmalade.client.handlers.ErrorResponseException;

public class CapsuleException extends ErrorResponseException
{
   private static final String HINT_500 = "If you see a HTTP 500 error when using a POST or PUT method on the API this is most often cause by our servers were not able to parse the incoming XML or JSON document. Common problems to look out for when creating documents:\n\n"
         + "White space appearing before the XML Declaration (i.e. <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\">). These must appear as the very start of your request body."
         + "Incorrectly encoded characters in XML or JSON; for example &, < and > must be encoded as &amp;, &lt; and &gt; respectively in your XML.\nhttp://developer.capsulecrm.com/v1/errors/";

   private static final String HINT_401 = "Make sure that the API token is included in your request; many libraries include a method for setting an HTTP basic authenthication header for you including encoding the API token correctly - but where that is not included you’ll need to set this as a header in your requests.\nhttp://developer.capsulecrm.com/v1/errors/";

   private static final long serialVersionUID = -4789631095631361954L;

   private static String getHint(Response response)
   {
      String hint;
      int status = response.status();
      if (status >= 500) {
         hint = HINT_500;
      } else if (status == 401) {
         hint = HINT_401;
      } else {
         hint = "See http://developer.capsulecrm.com/v1/errors/";
      }
      return String.format("* %s *\n%s", response.statusLine(), hint);
   }

   public CapsuleException(Exception e)
   {
      super(e);
   }

   public CapsuleException(Response response)
   {
      super(getHint(response), response);
   }

}
