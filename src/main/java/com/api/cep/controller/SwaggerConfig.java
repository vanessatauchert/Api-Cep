package com.api.cep.controller;


import com.api.cep.ExcludeFromJacocoGeneratedReport;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
@ExcludeFromJacocoGeneratedReport
@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customApi() {
        final String securitySchemeName = "basicAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("API Consulta CEP")
                        .description("Desafio Bradesco WirePro")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Vanessa Oliveira")
                                .email("tauchertvanessa@gmail.com")
                                .url("https://www.linkedin.com/in/vanessatauchert/"))
                )
                .externalDocs(new ExternalDocumentation()
                        .url("https://github.com/vanessatauchert/Api-Cep"));
    }











}
