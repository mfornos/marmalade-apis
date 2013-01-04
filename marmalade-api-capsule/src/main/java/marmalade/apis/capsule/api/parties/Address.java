package marmalade.apis.capsule.api.parties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("address")
public class Address extends Contact
{
   public enum Type {
      Home, Office, Postal
   }

   private Type type;
   private String street;
   private String city;
   private String zip;
   private String state;
   private String country;

}
