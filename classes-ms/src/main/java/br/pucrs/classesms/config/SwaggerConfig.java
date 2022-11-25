package br.pucrs.classesms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value( "${swagger.custom.server:#{null}}")
    String customServer;

    @Bean
    public GroupedOpenApi api1() {
        return GroupedOpenApi.builder()
            .group("classes-ms-1.0")
            .pathsToMatch("/**")
            .build();
    }

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        OpenAPI openApi = new OpenAPI();
        if (customServer != null && customServer.length() > 0) {
            openApi.addServersItem(new Server().url(customServer));
        }
        return openApi
            .info(new Info()
                .title("Classes MS")
                .description("Application that represents the classes.")
                .version("v1")
//                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(new ExternalDocumentation()
                .description("Classes MS Repo")
                .url("https://github.com/pucrs-poli/constr-sw-2022-2-g7")
            )
            .addSecurityItem(new SecurityRequirement()
                .addList(securitySchemeName)
            )
            .components(new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                )
            );
    }
}

