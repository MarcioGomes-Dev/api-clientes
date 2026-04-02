package br.com.cotiinformatica.api_clientes.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clientes API")
                        .description("API para gerenciamento de clientes desenvolvida pela COTI Informática.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("COTI Informática")
                                .email("contato@cotiinformatica.com.br")
                        )
                );
    }
}