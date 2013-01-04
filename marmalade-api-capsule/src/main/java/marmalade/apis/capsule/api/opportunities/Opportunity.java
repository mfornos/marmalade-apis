package marmalade.apis.capsule.api.opportunities;

import static marmalade.apis.capsule.spi.ActiveCapsule.Read;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;

import java.util.Date;
import java.util.concurrent.Future;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.api.CapsuleEntity;
import marmalade.apis.capsule.spi.CapsuleException;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("opportunity")
public class Opportunity extends CapsuleEntity
{
   private static final Object LOCK = new Object();

   public static String PATH = "opportunity";

   public static Opportunity byId(Number id)
   {
      try {
         synchronized (LOCK) {
            Future<Opportunity> f = promiseById(id);
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<Opportunity> promiseById(Number id)
   {
      return Read(buildUri(PATH, id), Opportunity.class);
   }

   private String name;
   private String partyName;
   private Long partyId;
   private String description;
   private String currency;
   private Double value;
   private String durationBasis;
   private Integer duration;
   private Integer probability;
   private String owner;
   private Long milestoneId;
   private String milestoneName;
   private Date expectedCloseDate;
   private Date createdOn;
   private Date updatedOn;

   @Override
   public String path()
   {
      return PATH;
   }

}
