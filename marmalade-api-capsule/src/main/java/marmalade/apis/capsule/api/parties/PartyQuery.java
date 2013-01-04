package marmalade.apis.capsule.api.parties;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import marmalade.client.UriBuilder;

public class PartyQuery
{
   // XXX reuse?
   private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'hhmmss");
   static {
      format.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
   }
   public static PartyQuery byEmail(String email)
   {
      return new PartyQuery(null, email, null, null, null, null);
   }
   public static PartyQuery byEmail(String email, Date lastModified)
   {
      return new PartyQuery(null, email, null, lastModified, null, null);
   }
   public static PartyQuery byModificationDate(Date lastModified)
   {
      return new PartyQuery(null, null, null, lastModified, null, null);
   }
   public static PartyQuery byModificationDate(Date lastModified, int start, int limit)
   {
      return new PartyQuery(null, null, null, lastModified, start, limit);
   }

   /**
    * 
    * @param q
    *           The search term will be matched against name, telephone number
    *           and exact match on searchable custom fields.
    * @return
    */
   public static PartyQuery byQuery(String q)
   {
      return new PartyQuery(q, null, null, null, null, null);
   }

   public static PartyQuery byQuery(String q, Date lastModified)
   {
      return new PartyQuery(q, null, null, lastModified, null, null);
   }

   public static PartyQuery byTag(String tag)
   {
      return new PartyQuery(null, null, tag, null, null, null);
   }

   public static PartyQuery byTag(String tag, Date lastModified)
   {
      return new PartyQuery(null, null, tag, lastModified, null, null);
   }

   public static String toStandardTime(Date date)
   {
      return format.format(date);
   }

   private final String q;

   private final String email;

   private final Date lastModified;

   private final Integer start;

   private final Integer limit;

   private final String tag;
   public PartyQuery(String q, String email, String tag, Date lastModified, Integer start, Integer limit)
   {
      this.q = q;
      this.email = email;
      this.lastModified = lastModified;
      this.start = start;
      this.limit = limit;
      this.tag = tag;
   }

   public URI buildURI(UriBuilder builder)
   {
      if (q != null && !q.isEmpty()) {
         builder.addParameter("q", q);
      }
      if (email != null && !email.isEmpty()) {
         builder.addParameter("email", email);
      }
      if (lastModified != null) {
         builder.addParameter("lastModified", toStandardTime(lastModified));
      }
      if (start != null) {
         builder.addParameter("start", start);
      }
      if (limit != null) {
         builder.addParameter("limit", limit);
      }
      if (tag != null) {
         builder.addParameter("tag", tag);
      }

      return builder.build();
   }

}
