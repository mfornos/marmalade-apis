package marmalade.apis.capsule.api.tasks;

import static marmalade.apis.capsule.spi.ActiveCapsule.EmptyPost;
import static marmalade.apis.capsule.spi.ActiveCapsule.Read;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;

import java.util.Date;
import java.util.concurrent.Future;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.api.CapsuleEntity;
import marmalade.apis.capsule.spi.CapsuleException;
import marmalade.client.Response;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("task")
public class Task extends CapsuleEntity
{
   private static final Object LOCK = new Object();
   public static final String PATH = "task";

   public static Task byId(Number taskId)
   {
      try {
         synchronized (LOCK) {
            Future<Task> f = promiseById(taskId);
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<Task> promiseById(Number taskId)
   {
      return Read(buildUri(PATH, taskId), Task.class);
   }

   private Long partyId;

   private String partyName;

   private String description;

   private String detail;

   private String owner;

   private String category;

   private Date dueDateTime;

   public Response complete()
   {
      try {
         return promiseComplete().get();
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   @Override
   public String path()
   {
      return PATH;
   }

   public Future<Response> promiseComplete()
   {
      return changeStatus("complete");
   }

   public Future<Response> promiseReopen()
   {
      return changeStatus("reopen");
   }

   public Response reopen()
   {
      try {
         return promiseReopen().get();
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   protected Future<Response> changeStatus(String status)
   {
      return EmptyPost(buildUri(PATH, id(), status));
   }
}
