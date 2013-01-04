package marmalade.apis.capsule.api;

import static marmalade.apis.capsule.spi.ActiveCapsule.Create;
import static marmalade.apis.capsule.spi.ActiveCapsule.Delete;
import static marmalade.apis.capsule.spi.ActiveCapsule.Update;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;

import java.util.concurrent.Future;

import lombok.Data;
import marmalade.apis.capsule.api.parties.Updatable;
import marmalade.apis.capsule.spi.CapsuleException;
import marmalade.client.Response;

@Data
public abstract class CapsuleEntity implements Updatable
{
   private Long id;

   public Response create()
   {
      try {
         Response response = promiseCreate().get();
         String location = response.header("location");
         String nid = location.substring(location.lastIndexOf('/') + 1);
         this.id = Long.parseLong(nid);
         return response;
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public Response delete()
   {
      try {
         return promiseDelete().get();
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   @Override
   public Long id()
   {
      return id;
   }

   @Override
   public String path()
   {
      return "";
   }

   public Future<Response> promiseCreate()
   {
      return Create(buildUri(createPath()), this);
   }

   public Future<Response> promiseDelete()
   {
      return Delete(buildUri(deletePath(), id));
   }

   public Future<Response> promiseSave()
   {
      return id == null ? promiseCreate() : promiseUpdate();
   }

   public Future<Response> promiseUpdate()
   {
      return promiseUpdate(id);
   }

   public Future<Response> promiseUpdate(Number partyId)
   {
      return Update(buildUri(updatePath(), partyId), onUpdate());
   }

   public Response save()
   {
      try {
         return promiseSave().get();
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   public Response update()
   {
      try {
         return promiseUpdate().get();
      } catch (Exception e) {
         throw new CapsuleException(e);
      }
   }

   protected String createPath()
   {
      return path();
   }

   protected String deletePath()
   {
      return path();
   }

   protected Object onUpdate()
   {
      return this;
   }

   protected String updatePath()
   {
      return path();
   }
}
