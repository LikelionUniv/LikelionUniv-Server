package likelion.univ.common.config;

import static likelion.univ.common.constant.RedisKey.GOOGLE_PUBLIC_KEYS;
import static likelion.univ.common.constant.RedisKey.KAKAO_PUBLIC_KEYS;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Profile("!test")
@RequiredArgsConstructor
@EnableCaching
@Configuration
public class RedisCacheManagerConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofDays(1L));

        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(customConfigurationMap(redisCacheConfiguration))
                .build();
        return redisCacheManager;
    }

    /* 커스텀하여 만료기간 설정 */
    private Map<String, RedisCacheConfiguration> customConfigurationMap(
            RedisCacheConfiguration redisCacheConfiguration) {
        Map<String, RedisCacheConfiguration> customConfigurationMap = new HashMap<>();
        customConfigurationMap.put(KAKAO_PUBLIC_KEYS, redisCacheConfiguration.entryTtl(Duration.ofDays(1L)));
        customConfigurationMap.put(GOOGLE_PUBLIC_KEYS, redisCacheConfiguration.entryTtl(Duration.ofDays(1L)));
        return customConfigurationMap;
    }
}
