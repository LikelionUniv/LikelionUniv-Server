package likelion.univ.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@EnableCaching
@Configuration
public class RedisCacheManagerConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                 .entryTtl(Duration.ofDays(1));

        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(customConfigurationMap(redisCacheConfiguration))
                .build();
        return redisCacheManager;
    }

    /* 커스텀하여 만료기간 설정 */
    private Map<String, RedisCacheConfiguration> customConfigurationMap(RedisCacheConfiguration redisCacheConfiguration) {
        Map<String, RedisCacheConfiguration> customConfigurationMap = new HashMap<>();
//        customConfigurationMap.put(RedisCacheKey.어쩌구, redisCacheConfiguration.entryTtl(Duration.ofSeconds(5L)));
//        customConfigurationMap.put(RedisCacheKey.저쩌구, redisCacheConfiguration.entryTtl(Duration.ofDays(1L)));
        return customConfigurationMap;
    }
}
