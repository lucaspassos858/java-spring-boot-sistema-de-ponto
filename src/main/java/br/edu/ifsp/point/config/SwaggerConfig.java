package br.edu.ifsp.point.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.edu.ifsp.point"))
                .paths(PathSelectors.ant("/*/**"))
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Sistema de Ponto",
                "Sistema de ponto desenvolvido para avaliação da disciplina de ADI - Sistemas Distribuídos",
                "API - 1.0",
                "Terms of service",
                new Contact("Bárbara, Bruno, Guilherme e Lucas", "https://github.com/lucaspassos858/java-spring-boot-sistema-de-ponto", ""),
                "License of API", "API license URL", Collections.emptyList());
    }

}
