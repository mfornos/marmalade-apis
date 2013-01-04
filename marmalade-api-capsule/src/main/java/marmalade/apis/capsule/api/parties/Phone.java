package marmalade.apis.capsule.api.parties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("phone")
public class Phone extends Contact
{
   public enum Type {
      Home, Work, Mobile, Fax, Direct
   }

   private String phoneNumber;
   private Type type;

}
