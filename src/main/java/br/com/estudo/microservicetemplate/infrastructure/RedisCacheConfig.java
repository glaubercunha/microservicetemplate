package br.com.estudo.microservicetemplate.infrastructure;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableCaching
public class RedisCacheConfig {

@Value("${spring.redis.host}")
String redisHost;

@Value("${spring.redis.port}")
Integer redisPort;

@Bean
public RedisCacheConfiguration cacheConfiguration() {
    log.info("[Cache] injecting TTL cache: enabled");
    return RedisCacheConfiguration.defaultCacheConfig()
      .entryTtl(Duration.ofSeconds(10))
      .disableCachingNullValues()
      .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
}

@Bean 
public RedisStandaloneConfiguration standaloneConfiguration() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setDatabase(0);
        return redisStandaloneConfiguration;
    }
}
