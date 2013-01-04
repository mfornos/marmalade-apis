package marmalade.apis.capsule.api.parties;

import static marmalade.apis.capsule.spi.ActiveCapsule.Delete;
import static marmalade.apis.capsule.spi.ActiveCapsule.Update;
import static marmalade.apis.capsule.spi.ActiveCapsule.buildUri;

import java.util.concurrent.Future;

import lombok.Data;
import lombok.EqualsAndHashCode;
import marmalade.apis.capsule.api.CapsuleEntity;
import marmalade.client.Response;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@EqualsAndHashCode(callSuper = false)
public class Contact extends CapsuleEntity
{
   public static final String PATH = "contact";

   @JsonBackReference
   private Contacts parent;

   @Override
   public String path()
   {
      return PATH;
   }

   // DELETE /api/party/:party-id/contact/:contact-id
   @Override
   public Future<Response> promiseDelete()
   {
      return Delete(buildUri(Party.PATH, parent.getParent().id(), path(), id()));
   }

   @Override
   public Future<Response> promiseUpdate()
   {
      return Update(buildUri(parent.getParent().path(), parent.getParent().id()), parent.getParent());
   }
}
