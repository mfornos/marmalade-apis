package marmalade.apis.capsule.api.parties;

import static marmalade.apis.capsule.spi.ActiveCapsule.Read;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;

import java.util.concurrent.Future;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.spi.ActiveCapsule;
import marmalade.apis.capsule.spi.CapsuleException;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("organisation")
public class Organisation extends Party
{
   private static final Object LOCK = new Object();
   private static final Object PPL_LOCK = new Object();

   public static String PATH = "organisation";

   public static Organisation byId(Number id)
   {
      try {
         synchronized (LOCK) {
            Future<Organisation> f = promiseById(id);
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static People people(Number organisationId)
   {
      try {
         synchronized (PPL_LOCK) {
            Future<People> f = promisePeople(organisationId);
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<Organisation> promiseById(Number id)
   {
      return ActiveCapsule.Read(ActiveCapsule.buildUri(Party.PATH, id), Organisation.class);
   }

   public static Future<People> promisePeople(Number organisationId)
   {
      return Read(buildUri(Party.PATH, organisationId, "people"), People.class);
   }

   private String name;

   public String getDeletePath()
   {
      return Party.PATH;
   }

   public String getName()
   {
      return name;
   }

   @Override
   public String path()
   {
      return PATH;
   }

   public Future<People> people()
   {
      return Read(buildUri(Party.PATH, getId(), "people"), People.class);
   }

}
