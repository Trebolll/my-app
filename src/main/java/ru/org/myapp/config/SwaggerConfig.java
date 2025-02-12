package ru.org.myapp.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
@ComponentScan(basePackages = "ru.org.myapp.controller")
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Weather API")
                        .version("1.0")
                        .description("API для получения данных о погоде и прогнозе"));
    }

    @Bean
    public OperationCustomizer customizeOperation() {
        return (operation, handlerMethod) -> {
            if (handlerMethod.getMethodAnnotation(GetMapping.class) != null) {
                operation.getParameters().forEach(parameter ->
                        parameter.in(ParameterIn.QUERY.toString()));
            }
            return operation;
        };
    }
}
