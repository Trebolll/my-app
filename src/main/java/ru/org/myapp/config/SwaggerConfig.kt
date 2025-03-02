package ru.org.myapp.config

import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.parameters.Parameter
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.method.HandlerMethod
import java.util.function.Consumer

@Configuration
@ComponentScan(basePackages = ["ru.org.myapp.controller"])
open class SwaggerConfig {
    @Bean
  open fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Weather API")
                    .version("1.0")
                    .description("API для получения данных о погоде и прогнозе")
            )
    }

    @Bean
    open fun customizeOperation(): OperationCustomizer {
        return OperationCustomizer { operation: Operation, handlerMethod: HandlerMethod ->
            if (handlerMethod.getMethodAnnotation(GetMapping::class.java) != null) {
                operation.parameters.forEach(Consumer { parameter: Parameter -> parameter.`in`(ParameterIn.QUERY.toString()) })
            }
            operation
        }
    }
}
