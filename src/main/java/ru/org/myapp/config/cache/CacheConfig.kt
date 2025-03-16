package ru.org.myapp.config.cache

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.org.myapp.util.Constant
import java.util.concurrent.TimeUnit

@Configuration
open class CacheConfig {
    @Bean
    open fun cacheManager(): CacheManager {
        return object : CaffeineCacheManager(Constant.forecast, Constant.weather) {
            init {
                setCaffeine(
                    Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .maximumSize(1000)
                )
            }
        }
    }
}
