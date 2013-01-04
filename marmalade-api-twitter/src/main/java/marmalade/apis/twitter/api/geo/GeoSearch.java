package marmalade.apis.twitter.api.geo;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import marmalade.client.UriBuilder;

@Data
public class GeoSearch
{
   public enum Granularity {
      poi, neighborhood, city, admin, country
   }

   public static GeoSearch searchBuilder()
   {
      return new GeoSearch();
   }

   public static GeoSearch searchBuilder(String query)
   {
      return searchBuilder().query(query);
   }

   private Double lat, lon;
   private String query;
   private String ip;
   private Granularity granularity;
   private String accuracy;
   private Integer maxResults;
   private String containedWithin;

   private Map<String, String> attributes;

   private String callback;

   public GeoSearch()
   {
      this.attributes = new HashMap<String, String>();
   }

   public URI buildUri(UriBuilder builder)
   {
      if (query != null) {
         builder.addParameter("query", query);
      }

      if (lat != null) {
         builder.addParameter("lat", lat);
      }

      if (lon != null) {
         builder.addParameter("long", lon);
      }

      // TODO all q...

      return builder.build();
   }

   public GeoSearch accuracy(String accuracy)
   {
      this.accuracy = accuracy;
      return this;
   }

   public GeoSearch attribute(String name, String value)
   {
      attributes.put(name, value);
      return this;
   }

   public GeoSearch callback(String callback)
   {
      this.callback = callback;
      return this;
   }

   public GeoSearch containedWithin(String containedWithin)
   {
      this.containedWithin = containedWithin;
      return this;
   }

   public GeoSearch granularity(Granularity granularity)
   {
      this.granularity = granularity;
      return this;
   }

   public GeoSearch ip(String ip)
   {
      this.ip = ip;
      return this;
   }

   public GeoSearch lat(double lat)
   {
      this.lat = lat;
      return this;
   }

   public GeoSearch lon(double lon)
   {
      this.lon = lon;
      return this;
   }

   public GeoSearch maxResults(int maxResults)
   {
      this.maxResults = maxResults;
      return this;
   }

   public GeoSearch query(String query)
   {
      this.query = query;
      return this;
   }
}
