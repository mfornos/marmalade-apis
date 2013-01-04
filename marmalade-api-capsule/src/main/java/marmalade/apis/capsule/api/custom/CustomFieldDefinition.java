package marmalade.apis.capsule.api.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.api.CapsuleEntity;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("customFieldDefinition")
public class CustomFieldDefinition extends CapsuleEntity
{

   public enum ForClass {
      PERSON, ORGANISATION
   }

   public enum Type {
      Text, List, Date, Boolean
   }

   private String tag;
   private String label;
   private String option;
   private ForClass forClass;
   private Type type;

}
