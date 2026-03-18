package com.raag.movie_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI movieServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🎬 Movie Catalog API")
                        .description("Spring Boot REST API for managing movies")
                        .version("1.0.0"));
    }
}
