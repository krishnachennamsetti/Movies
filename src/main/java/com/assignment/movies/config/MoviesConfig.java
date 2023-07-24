package com.assignment.movies.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoviesConfig {

    /**
     * Open API configuration bean for documentation
     * @return OpenAPI config bean
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components())
                .info(new Info().title("Movies Application").description(
                        "Movies is a microservice application designed to check whether movie won best picture oscar, give rating and fetch the top 10 rated movies based on their box office value"));
    }

}
