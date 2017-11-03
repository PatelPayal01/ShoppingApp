package application;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import service.CustomerService;
import service.HelloWorldService;
import service.SignUp_LoginService;


@ApplicationPath("api")
public class MessageApplication
  extends Application
{
  private Set<Object> singletons = new HashSet();
  
  public MessageApplication() {
    singletons.add(new HelloWorldService());
    singletons.add(new SignUp_LoginService());
    singletons.add(new CustomerService());
  }
  
  public Set<Object> getSingletons()
  {
    if (singletons == null) {
      CorsFilter corsFilter = new CorsFilter();
      corsFilter.getAllowedOrigins().add("*");
      
      singletons = new LinkedHashSet();
      singletons.add(corsFilter);
    }
    return singletons;
  }
}
