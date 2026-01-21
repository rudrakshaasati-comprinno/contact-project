package com.comprinno.contactappbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        System.out.println("🔵 STEP-SWAGGER-1: SwaggerConfig loaded");
        System.out.println("🔵 STEP-SWAGGER-2: Creating OpenAPI bean");

        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("Contact App Backend API")
                        .version("1.0")
                        .description("API Documentation for Contact Record Keeping App"));

        System.out.println("🟢 STEP-SWAGGER-3: OpenAPI bean created successfully");

        return openAPI;
    }
}