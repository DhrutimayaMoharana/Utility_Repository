package com.email.email_service.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {    
    
    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
        		.displayName("Email Service")
                .group("controller-api")
                .packagesToScan("com.email.email_service.controller") // Specify the package to scan
                .build();
    }
	
}
