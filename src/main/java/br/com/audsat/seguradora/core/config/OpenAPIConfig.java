package br.com.audsat.seguradora.core.config;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${audsat.openapi.dev-url}")
    private String devUrl;

    @Value("${audsat.openapi.prod-url}")
    private String prodUrl;

    String schemeName = "authorization";
    String bearerFormat = "JWT";
    String scheme = "bearer";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("ads.eduardo.silva@gmail.com");
        contact.setName("Eduardo Pereira");
        contact.setUrl("https://www.linkedin.com/in/eduardo-pereira-software/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Insurance company management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage insurance.")
                .license(mitLicense);

        SecurityScheme securityScheme = new SecurityScheme()
                .name(schemeName)
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat(bearerFormat)
                .scheme(scheme)
                .description("security description");

        return new OpenAPI().info(info).addSecurityItem(
                        new SecurityRequirement().addList(schemeName))
                .components(new Components().addSecuritySchemes(schemeName, securityScheme))
                .servers(List.of(devServer, prodServer));
    }
}