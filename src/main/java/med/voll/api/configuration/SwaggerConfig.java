package med.voll.api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiMedVollAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api Med Voll")
                        .version("v1")
                        .description("Documentação Api Med Voll")
                );
    }
}
