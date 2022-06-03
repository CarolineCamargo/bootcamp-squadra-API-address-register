package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

    @Configuration
    @EnableSwagger2
    public class ConfigSwagger {

        @Bean
        public Docket docket() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("br.com.squadra.bootcamp.desafiofinal" +
                            ".carolinedecamargo.addressregisterservice.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo());
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Meetups API")
                    .description("RestAPI - Serviço de cadastro de endereços")
                    .version("1.0")
                    .contact(contact())
                    .build();
        }

        private Contact contact() {
            return new Contact("Caroline de Camargo Flach",
                    "https://www.linkedin.com/in/caroline-de-camargo-8030081a3/",
                    "caroline.flach@hotmail.com.br");
        }
    }
