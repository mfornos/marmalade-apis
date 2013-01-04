package marmalade.apis.capsule.api.custom;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("customFieldDefinitions")
public class CustomFieldDefinitions
{

   public static String PATH = "customfield/definitions";

   private Set<CustomFieldDefinition> customFieldDefinition;

   public boolean isEmpty()
   {
      return customFieldDefinition == null || customFieldDefinition.isEmpty();
   }

}
