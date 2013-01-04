package marmalade.apis.capsule.api.parties;

import static marmalade.apis.capsule.spi.ActiveCapsule.Read;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;
import static marmalade.apis.capsule.spi.ActiveCapsule.uriBuilder;

import java.util.Date;
import java.util.concurrent.Future;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.api.CapsuleEntity;
import marmalade.apis.capsule.api.custom.CustomFieldDefinitions;
import marmalade.apis.capsule.api.custom.CustomFields;
import marmalade.apis.capsule.spi.CapsuleException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Party extends CapsuleEntity
{
   private static final Object ALL_LOCK = new Object();
   private static final Object FDEFS_LOCK = new Object();
   private static final Object FIND_LOCK = new Object();

   public static String PATH = "party";

   public static Parties all()
   {
      try {
         synchronized (ALL_LOCK) {
            Future<Parties> f = promiseFind(PartyQuery.byQuery(""));
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static CustomFieldDefinitions fieldDefinitions()
   {
      try {
         synchronized (FDEFS_LOCK) {
            Future<CustomFieldDefinitions> f = promiseFieldDefinitions();
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Parties find(PartyQuery query)
   {
      try {
         synchronized (FIND_LOCK) {
            Future<Parties> f = promiseFind(query);
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<CustomFieldDefinitions> promiseFieldDefinitions()
   {
      return Read(buildUri(PATH, CustomFieldDefinitions.PATH), CustomFieldDefinitions.class);
   }

   public static Future<Parties> promiseFind(PartyQuery query)
   {
      return Read(query.buildURI(uriBuilder(PATH)), Parties.class);
   }

   private String about;

   private String pictureURL;

   @JsonManagedReference
   private Contacts contacts;

   private Date createdOn;

   private Date updatedOn;

   @JsonIgnore
   private CustomFields customFields;

   public CustomFields getCustomFields()
   {
      if (customFields == null) {
         customFields = CustomFields.byId(Party.PATH, id());
      }
      return customFields;
   }

   @JsonIgnore
   public Address getFirstAddress()
   {
      return hasContacts() ? contacts.getFirstAddress() : null;
   }

   @JsonIgnore
   public Email getFirstEmail()
   {
      return hasContacts() ? contacts.getFirstEmail() : null;
   }

   @JsonIgnore
   public Phone getFirstPhone()
   {
      return hasContacts() ? contacts.getFirstPhone() : null;
   }

   @JsonIgnore
   public Website getFirstWebsite()
   {
      return hasContacts() ? contacts.getFirstWebsite() : null;
   }

   @JsonIgnore
   public boolean hasContacts()
   {
      return !(contacts == null || contacts.isEmpty());
   }

}
