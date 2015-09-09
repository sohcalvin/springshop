package csoh.reference.springshop.configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringShopInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    
	       @Override
	       protected Class<?>[] getRootConfigClasses() {
	    	   return new Class[] { SpringShopConfiguration.class };
	       }
	     
	       @Override
	       protected Class<?>[] getServletConfigClasses() {
	           return null;
	       }
	     
	       @Override
	       protected String[] getServletMappings() {
	           return new String[] { "/" };
	       }
	    
	
}
