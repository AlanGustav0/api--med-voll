package med.voll.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    public void addCorsMapping(CorsRegistry regustry){
        regustry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","DELETE","OPTIONS","HEAD","TRACE","CONNECT");
    }
}
