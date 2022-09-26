package br.pucrs.auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api1() {
        return GroupedOpenApi.builder()
            .group("auth-api-1.0")
            .pathsToMatch("/**")
            .build();
    }

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
            .info(new Info()
                .title("Auth API")
                .description("Auth application using keycloak.")
                .version("v1")
//                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(new ExternalDocumentation()
                    .description("Auth Repo")
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
