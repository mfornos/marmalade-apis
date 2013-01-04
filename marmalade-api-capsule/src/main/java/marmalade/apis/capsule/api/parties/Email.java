package marmalade.apis.capsule.api.parties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("email")
public class Email extends Contact
{
   public enum Type {
      Work, Home
   }

   private Type type;
   private String emailAddress;

}
