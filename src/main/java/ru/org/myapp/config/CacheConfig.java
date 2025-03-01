package ru.org.myapp.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new CaffeineCacheManager("forecast", "weather") {{
            setCaffeine(Caffeine.newBuilder()
                    .expireAfterWrite(1, TimeUnit.HOURS)
                    .maximumSize(1000));
        }};
    }
}
