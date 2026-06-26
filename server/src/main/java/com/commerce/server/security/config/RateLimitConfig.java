package com.commerce.server.security.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    @Bean
    public static Bucket createBucket(){
        Bandwidth limit = Bandwidth.builder().capacity(5).refillGreedy(5,Duration.ofMinutes(1)).build();
        return Bucket.builder().addLimit(limit).build();
    }

}
