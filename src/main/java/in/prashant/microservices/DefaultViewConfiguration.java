package in.prashant.microservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
public class DefaultViewConfiguration implements WebMvcConfigurer{

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/upload.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
      }
}