package marmalade.apis.capsule.api.parties;

import java.util.Set;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@Data
@JsonRootName("people")
public class People
{
   public class Parties
   {
      public Set<Person> person;

      @Override
      public String toString()
      {
         return Objects.toStringHelper(this).add("person", person).toString();
      }
   }

   private Parties parties;

}
