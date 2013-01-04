package marmalade.apis.twitter.api.geo;

import java.util.Set;

import lombok.Data;

@Data
public class PlacesResult
{

   // XXX https://jira.codehaus.org/browse/JACKSON-781
   private Result result;

   @Data
   public static class Result
   {
      private Set<Place> places;
      private String token;
   }
   
}
