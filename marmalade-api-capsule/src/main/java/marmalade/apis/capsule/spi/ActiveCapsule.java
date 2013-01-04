package marmalade.apis.capsule.spi;

import static marmalade.Marmalade.Get;
import static marmalade.Marmalade.Post;
import static marmalade.Marmalade.Put;
import static marmalade.client.UriBuilder.uriBuilderFrom;

import java.net.URI;
import java.util.concurrent.Future;

import marmalade.Marmalade;
import marmalade.client.Response;
import marmalade.client.UriBuilder;
import marmalade.client.async.AsyncClient;
import marmalade.client.handlers.ErrorHandler;
import marmalade.spi.Registry;

import org.apache.http.entity.ContentType;

public final class ActiveCapsule
{
   private static final String NAME_SPACE = "cpsl";
   private static String apiURI;
   private static ErrorHandler EH = new CapsuleErrorHandler();

   public static String apiURI()
   {
      if (apiURI == null) {
         apiURI = ((CapsuleClient) Registry.lookup(NAME_SPACE, AsyncClient.class)).getCapsuleHost();
      }
      return apiURI;
   }

   public static URI buildUri(Object... paths)
   {
      return uriBuilder(paths).build();
   }

   public static Future<Response> Create(URI uri, Object bean)
   {
      return defaults(Post(uri)).bean(bean).as(ContentType.APPLICATION_JSON).sendAsync();
   }

   public static Future<Response> Delete(URI uri)
   {
      return defaults(Marmalade.Delete(uri)).sendAsync();
   }

   public static Future<Response> EmptyPost(URI uri)
   {
      return defaults(Post(uri)).sendAsync();
   }

   public static <T> Future<T> Read(URI uri, Class<T> type)
   {
      return defaults(Get(uri)).mapAsync(type);
   }

   public static Future<Response> Update(URI uri, Object bean)
   {
      return defaults(Put(uri)).bean(bean).as(ContentType.APPLICATION_JSON).sendAsync();
   }

   public static UriBuilder uriBuilder(Object... paths)
   {
      return uriBuilderFrom(apiURI()).appendPaths(paths);
   }

   private static Marmalade defaults(Marmalade m)
   {
      return m.ns(NAME_SPACE).setAccept(ContentType.APPLICATION_JSON).withErrorHandler(EH);
   }
}
