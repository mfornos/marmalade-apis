package marmalade.apis.capsule.api.parties;

import java.util.concurrent.Future;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.spi.ActiveCapsule;
import marmalade.apis.capsule.spi.CapsuleException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("person")
public class Person extends Party
{
   private static final Object LOCK = new Object();

   public static String PATH = "person";

   public static Person byId(Number id)
   {
      try {
         synchronized (LOCK) {
            Future<Person> f = promiseById(id);
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<Person> promiseById(Number id)
   {
      return ActiveCapsule.Read(ActiveCapsule.buildUri(Party.PATH, id), Person.class);
   }

   private String firstName;
   private String lastName;

   private String title;
   private String jobTitle;

   private String organisationName;
   private Long organisationId;

   public String deletePath()
   {
      return Party.PATH;
   }

   @JsonIgnore
   public String getName()
   {
      return firstName + " " + lastName;
   }

   @Override
   public String path()
   {
      return PATH;
   }

}
