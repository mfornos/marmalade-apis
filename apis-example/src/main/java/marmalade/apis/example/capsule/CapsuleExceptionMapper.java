package marmalade.apis.example.capsule;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import marmalade.apis.capsule.spi.CapsuleException;

@Provider
public class CapsuleExceptionMapper implements ExceptionMapper<CapsuleException>
{

   public Response toResponse(CapsuleException exception)
   {
      int statusCode = exception.getStatusCode();
      Status status = statusCode > 0 ? Status.fromStatusCode(statusCode) : Response.Status.INTERNAL_SERVER_ERROR;
      return Response.status(status).entity(errorPage(exception)).build();
   }

   private String errorPage(CapsuleException exception)
   {
      StringBuilder text = new StringBuilder();
      text.append("<html><head><title>Capsule Error</title><link rel=\"stylesheet\" href=\"/_/css/style.css\"></head>");
      text.append("<body id=\"error-page\"><div class=\"wrapper\"><header><h1>Capsule ERROR</h1></header><article><div class=\"error\"><pre>");
      // XXX
      text.append(exception.getCause().getMessage());
      text.append("</pre></div><div class=\"cause\"><pre>");
      text.append(exception.getStackTrace());
      text.append("</pre></div></article></div></body></html>");
      return text.toString();
   }

}
