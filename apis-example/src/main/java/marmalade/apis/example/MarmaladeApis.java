package marmalade.apis.example;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import marmalade.apis.example.capsule.CapsuleExceptionMapper;
import marmalade.apis.example.capsule.CapsuleResource;
import marmalade.apis.example.twitter.TwitterExceptionMapper;
import marmalade.apis.example.twitter.TwitterResource;

@ApplicationPath("/apis")
public class MarmaladeApis extends Application
{
   @Override
   public Set<Class<?>> getClasses()
   {
      final Set<Class<?>> classes = new HashSet<Class<?>>();
      classes.add(CapsuleResource.class);
      classes.add(CapsuleExceptionMapper.class);
      classes.add(TwitterResource.class);
      classes.add(TwitterExceptionMapper.class);
      return classes;
   }
}
