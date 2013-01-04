package marmalade.apis.capsule.api.custom;

import static marmalade.apis.capsule.spi.ActiveCapsule.Read;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;

import java.util.Set;
import java.util.concurrent.Future;

import lombok.Data;
import marmalade.apis.capsule.spi.CapsuleException;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@JsonRootName("customFields")
public class CustomFields
{
   private static final Object LOCK = new Object();

   public static final String PATH = "customfields";

   public static CustomFields byId(String path, Long id)
   {
      try {
         synchronized (LOCK) {
            Future<CustomFields> f = promiseById(path, id);
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<CustomFields> promiseById(String path, Long id)
   {
      return Read(buildUri(path, id, PATH), CustomFields.class);
   }

   private Set<CustomField> customField;

   public boolean isEmpty()
   {
      return customField == null || customField.isEmpty();
   }

   // TODO custom updates, remove, add
   // PUT /api/party/:id/customfields
   // PUT /api/opportuntity/:id/customfields
   // PUT /api/kase/:id/customfields
}
