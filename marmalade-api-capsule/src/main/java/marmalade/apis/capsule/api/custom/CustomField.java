package marmalade.apis.capsule.api.custom;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.api.CapsuleEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("customField")
public class CustomField extends CapsuleEntity
{
   private String tag;

   private String label;

   private String text;

   private Date date;

   @JsonProperty("boolean")
   private Boolean booleanValue;

   // @JsonIgnore
   // public Object getValue() {
   //
   // }
}
