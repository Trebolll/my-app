package ru.org.myapp.config

import com.github.prominence.openweathermap.api.OpenWeatherMapClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class OpenWeatherMapConfig {
    @Value("\${open.weather.sdk.key}")
    private val key: String? = null

    @Bean
    open fun openWeatherMapClient(): OpenWeatherMapClient {
        return OpenWeatherMapClient(key)
    }
}
