package com.msbahrddin.upscaletest;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "upscale`s Employee Management REST APIs docs",
                description = "Spring Boot REST API Documentation for upscale test",
                version = "v3.0",
                contact = @Contact(
                        name = "Safar Baharuddin",
                        email = "muhammad.ict1487@gmail.com",
                        url = "https://github.com/muhammadsafar/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.com/"

                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Department Management Documentation",
                url = "http:8080/api/v1"
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "localhost testing"
        )
)
public class UpscaleTestApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(UpscaleTestApplication.class, args);
    }

}
