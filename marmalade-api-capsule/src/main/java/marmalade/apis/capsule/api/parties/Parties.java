package marmalade.apis.capsule.api.parties;

import java.util.Collection;
import java.util.Set;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@JsonRootName("parties")
public class Parties
{

   private Set<Person> person;

   private Set<Organisation> organisation;

   public boolean isEmpty()
   {
      return isEmpty(person) && isEmpty(organisation);
   }

   private boolean isEmpty(Collection<?> c)
   {
      return c == null || c.isEmpty();
   }

}
