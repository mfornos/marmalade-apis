package marmalade.apis.capsule.api.parties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("website")
public class Website extends Contact
{

   public enum Type {
      Work, Home
   }

   public enum WebService {
      URL, SKYPE, TWITTER, FACEBOOK, LINKED_IN, XING, FEED, GOOGLE_PLUS, FLICKR, GITHUB, YOUTUBE
   }

   private String webAddress;
   private String url;
   private WebService webService;
   private Type type;

}
