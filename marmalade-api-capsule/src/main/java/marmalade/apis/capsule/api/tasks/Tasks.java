package marmalade.apis.capsule.api.tasks;

import static marmalade.apis.capsule.spi.ActiveCapsule.Read;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;

import java.util.Set;
import java.util.concurrent.Future;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.spi.CapsuleException;

import com.fasterxml.jackson.annotation.JsonRootName;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("tasks")
public class Tasks
{

   public static final Object LOCK = new Object();
   public static final String PATH = "tasks";

   public static Tasks openTasks()
   {
      try {
         synchronized (LOCK) {
            Future<Tasks> f = promiseOpenTasks();
            return f.get();
         }
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public static Future<Tasks> promiseOpenTasks()
   {
      return Read(buildUri(PATH), Tasks.class);
   }

   private Set<Task> task;

   public boolean isEmpty()
   {
      return task == null || task.isEmpty();
   }
}
