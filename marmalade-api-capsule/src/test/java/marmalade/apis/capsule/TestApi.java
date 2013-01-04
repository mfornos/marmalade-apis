package marmalade.apis.capsule;

import static marmalade.apis.capsule.spi.ActiveCapsule.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import marmalade.Marmalade;
import marmalade.apis.capsule.api.parties.Address;
import marmalade.apis.capsule.api.parties.Contacts;
import marmalade.apis.capsule.api.parties.Email;
import marmalade.apis.capsule.api.parties.Organisation;
import marmalade.apis.capsule.api.parties.Parties;
import marmalade.apis.capsule.api.parties.Party;
import marmalade.apis.capsule.api.parties.PartyQuery;
import marmalade.apis.capsule.api.parties.Person;
import marmalade.apis.capsule.api.tasks.Task;
import marmalade.client.Response;
import marmalade.test.http.BaseHttpTest;
import marmalade.test.http.Condition;
import marmalade.test.http.SerializableResponse;
import marmalade.test.http.replay.SerializationUtil;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TestApi extends BaseHttpTest
{
   //
   // @Test
   // public void w() throws InterruptedException, ExecutionException {
   // Marmalade req = Marmalade.Get(buildUri(Party.PATH,
   // 34178831)).ns("cpsl").setAccept(ContentType.APPLICATION_JSON);
   // Response response = req.sendAsync().get();
   // SerializationUtil.serialize("src/test/resources/data/organisation",response.getHttpResponse());
   //
   // }

   @Test
   public void parties()
   {
      server.expect(Condition.when("GET").path("/api/party").respond(200).replay("src/test/resources/data/parties-qlimit"));

      Parties parties = Party.all();
      Assert.assertNotNull(parties);
      Assert.assertEquals(parties.getPerson().size(), 1);

      Person p = parties.getPerson().iterator().next();
      Organisation o = parties.getOrganisation().iterator().next();

      Assert.assertEquals(p.getName(), "Boi78 Cesariun");
      Assert.assertEquals(o.getName(), "Amneris");
   }

   @Test
   public void person()
   {
      server.expect(Condition.when("GET").path("/api/party/1").respond(200).replay("src/test/resources/data/person"));

      Person person = Person.byId(1L);
      Assert.assertNotNull(person);
      Assert.assertEquals(person.getFirstEmail().getEmailAddress(), "dsd@jio.com");
   }

   @Test
   public void organisation()
   {
      server.expect(Condition.when("GET").path("/api/party/1").respond(200).replay("src/test/resources/data/organisation"));

      Organisation org = Organisation.byId(1L);
      Assert.assertNotNull(org);
      Assert.assertEquals(org.getName(), "Amneris");
   }

   // @Test
   public void a() throws InterruptedException, ExecutionException, JsonProcessingException
   {
      // 34176629 34178832
      Person p = Person.byId(34179943);
      Set<Email> email = p.getContacts().getEmail();
      System.out.println(email);
      Email ee = email.iterator().next();
      ee.setType(Email.Type.Work);
      ee.update();
      // ee.delete();

      // System.out.println(p.hashCode());
      // System.out.println(p.equals("joi"));

      // ----
      // Calendar cal = Calendar.getInstance();
      // cal.set(Calendar.MONTH, 2);

      // Parties res = Party.find(PartyQuery.byQuery("a", cal.getTime()));
      // System.out.println(res);

      // Future<Response> all = Task.all();
      // System.out.println(all.get().asString());

      // 8609744
      // Future<Task> task = Task.read(8609744);
      // System.out.println(task.get());

      // Person np = new Person();
      // np.firstName = "Joel";
      // Future<Response> r = np.update(34178832);

      // // Person c = party.person;
      // Person c = Person.byId(34178832);
      // c.firstName = "Munch";
      // c.lastName = "Norris";
      // //
      // //
      // Response response = c.update();
      // System.out.println(response);

      // Future<Response>p= Party.t(34178831);
      // Response response = p.get();
      // System.out.println(response.asString());

      // Future<People>p= Party.people(34178831);
      // People response = p.get();
      // System.out.println(response);

      // createP();
   }

   private void createP() throws InterruptedException, ExecutionException, JsonProcessingException
   {
      Person person = new Person();
      person.setAbout("jiojojoj");
      person.setFirstName("Boi78");
      person.setLastName("Cesariun");
      person.setContacts(new Contacts());
      person.getContacts().setAddress(new HashSet<Address>());
      Address address = new Address();
      address.setType(Address.Type.Postal);
      address.setCity("bcn");
      address.setStreet("blablabalabl 3408943");
      person.getContacts().getAddress().add(address);

      // ObjectMapper mapper =
      // Registry.lookupMapper("cpsl",ContentType.APPLICATION_JSON);
      // System.out.println(mapper.writeValueAsString(person));

      Response response = person.create();
      System.out.println(response);

      System.out.println("New person id: " + person.getId());
      // response = person.delete();
      System.out.println(response);
   }

}
