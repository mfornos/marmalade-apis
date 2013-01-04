package marmalade.apis.example.capsule;

import static java.lang.String.format;
import static marmalade.apis.capsule.api.parties.PartyQuery.byQuery;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import marmalade.apis.capsule.api.opportunities.Opportunities;
import marmalade.apis.capsule.api.parties.Organisation;
import marmalade.apis.capsule.api.parties.Parties;
import marmalade.apis.capsule.api.parties.Party;
import marmalade.apis.capsule.api.parties.Person;
import marmalade.apis.capsule.spi.CapsuleException;
import marmalade.apis.example.BaseResource;

@Path("capsule")
public class CapsuleResource extends BaseResource
{

   @GET
   @Path("party/delete/organisation/{id}")
   public Response deleteOrganisation(@PathParam("id") Long id) throws ServletException, IOException,
         URISyntaxException
   {
      Organisation model = Organisation.byId(id);
      return deleteParty(model);
   }

   @GET
   @Path("party/delete/person/{id}")
   public Response deletePerson(@PathParam("id") Long id) throws ServletException, IOException, URISyntaxException
   {
      Person model = Person.byId(id);
      return deleteParty(model);
   }

   @GET
   @Path("party/organisation/{id}")
   public Response getOrganisation(@PathParam("id") Long id) throws ServletException, IOException
   {
      Organisation model = Organisation.byId(id);
      return forwardModel(model);
   }

   @GET
   @Path("parties")
   public Response getParties(@QueryParam("q") String query) throws ServletException, IOException
   {
      Parties model = Party.find(byQuery(query));
      return forwardModel(model);
   }

   @GET
   @Path("opportunities")
   public Response getOpportunities() throws ServletException, IOException
   {
      Opportunities model = Opportunities.find();
      return forwardModel(model);
   }

   @GET
   @Path("party/person/{id}")
   public Response getPerson(@PathParam("id") Long id) throws ServletException, IOException
   {
      Person model = Person.byId(id);
      return forwardModel(model);
   }

   private Response deleteParty(Party model) throws URISyntaxException
   {
      String partyType = model.getClass().getSimpleName().toLowerCase();
      try {
         model.delete();

         flashSuccess(format("Deleted %s with id %s", partyType, model.getId()));
         return redirect("/apis/capsule");

      } catch (CapsuleException e) {
         flashError(format("Error deleting %s with id %s.\n%s", partyType, model.getId(), e.getMessage()));
         return redirect(format("/apis/capsule/party/%s/%s", partyType, model.getId()));
      }
   }

}
