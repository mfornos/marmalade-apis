package marmalade.apis.capsule.api.parties;

import java.util.Collection;
import java.util.Set;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@JsonRootName("contacts")
public class Contacts
{

   @JsonManagedReference
   private Set<Email> email;

   @JsonManagedReference
   private Set<Address> address;

   @JsonManagedReference
   private Set<Website> website;

   @JsonManagedReference
   private Set<Phone> phone;

   @JsonBackReference
   private Updatable parent;

   @JsonIgnore
   public Address getFirstAddress()
   {
      return getFirst(address);
   }

   @JsonIgnore
   public Email getFirstEmail()
   {
      return getFirst(email);
   }

   @JsonIgnore
   public Phone getFirstPhone()
   {
      return getFirst(phone);
   }

   @JsonIgnore
   public Website getFirstWebsite()
   {
      return getFirst(website);
   }

   public boolean isEmpty()
   {
      return isEmpty(email) && isEmpty(address) && isEmpty(website) && isEmpty(phone);
   }

   private <T> T getFirst(Set<T> set)
   {
      return (set == null || set.isEmpty()) ? null : set.iterator().next();
   }

   private boolean isEmpty(Collection<?> c)
   {
      return c == null || c.isEmpty();
   }

}
