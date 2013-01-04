package marmalade.apis.capsule.api.opportunities;

import java.util.Set;
import java.util.concurrent.Future;

import lombok.Data;
import marmalade.apis.capsule.spi.CapsuleException;
import static marmalade.apis.capsule.spi.ActiveCapsule.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@JsonRootName("opportunities")
public class Opportunities
{
   private static final Object LOCK = new Object();

   private Set<Opportunity> opportunity;

   public static Opportunities find()
   {
      try {
         synchronized (LOCK) {
            Future<Opportunities> f = promiseFind();
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<Opportunities> promiseFind()
   {
      return Read(buildUri(Opportunity.PATH), Opportunities.class);
   }

   @JsonIgnore
   public boolean isEmpty()
   {
      return opportunity == null || opportunity.isEmpty();
   }

}
