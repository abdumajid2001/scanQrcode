package abj.scanQrcode.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenAPIConfig {

    @Value("${baseUrl}")
    private String baseUrl;

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl(baseUrl);
        server.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("itm.mchj@itm-llc.uz");
        contact.setName("CENTER OF INNOVATIVE TECHNOLOGIES");
        contact.setUrl("https://www.itm-llc.uz/");


        Info info = new Info()
                .title("Demo Service API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage demo.")
                .termsOfService("https://www.itm-llc.uz/");

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(info)
                .servers(List.of(server));
    }

}
