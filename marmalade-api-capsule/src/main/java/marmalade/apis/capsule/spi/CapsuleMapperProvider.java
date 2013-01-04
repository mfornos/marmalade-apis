package marmalade.apis.capsule.spi;

import marmalade.spi.MapperProvider;
import marmalade.spi.Named;

import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Named("cpsl")
public class CapsuleMapperProvider implements MapperProvider
{

   @Override
   public ObjectMapper mapper()
   {
      ObjectMapper mapper = new ObjectMapper();
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
      mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

      mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
      mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
      mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
      mapper.setSerializationInclusion(Include.NON_NULL);

      // XXX :_
      // mapper.registerModule(new AfterburnerModule());
      return mapper;
   }

   @Override
   public String mimeType()
   {
      return ContentType.APPLICATION_JSON.getMimeType();
   }

}
