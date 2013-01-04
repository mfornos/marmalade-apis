package marmalade.apis.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

public abstract class BaseResource
{
   @Context
   HttpServletRequest request;
   @Context
   HttpServletResponse response;
   @Context
   ServletContext context;
   
   @GET
   public Response getIndex() throws ServletException, IOException
   {
      return forward("/index.jsp");
   }

   protected void flashError(String msg)
   {
      request.setAttribute("flash.errorMessage", msg);
   }

   protected void flashSuccess(String msg)
   {
      request.setAttribute("flash.successMessage", msg);
   }

   protected Response forward(String uri) throws ServletException, IOException
   {
      context.getRequestDispatcher(uri).forward(request, response);
      return Response.ok().build();
   }

   protected Response forwardModel(Object model) throws ServletException, IOException
   {
      return forwardModel(model, model.getClass().getSimpleName().toLowerCase());
   }

   protected Response forwardModel(Object model, String template) throws ServletException, IOException
   {
      request.setAttribute("model", model);
      return forward(String.format("/WEB-INF/pages/%s.jsp", template));
   }

   protected Response redirect(String uri) throws URISyntaxException
   {
      return Response.seeOther(new URI(uri)).build();
   }
}
