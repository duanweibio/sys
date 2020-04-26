package com.dwb;

import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

@Configuration
@EnableCaching
public class RedieConfig extends CachingConfigurerSupport {
	
	@Bean(name="redisTemplate")
	@ConditionalOnMissingBean(name="redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(
			RedisConnectionFactory factory){
		RedisTemplate<Object, Object> template = new RedisTemplate<Object,Object>();
		template.setConnectionFactory(factory);
		setSerializer(template);
		return template;
	}

	@Bean(name="redisCacheManager")
	public CacheManager cacheManager(RedisConnectionFactory factory){
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(100));
		RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
		ParserConfig.getGlobalInstance().addAccept("com.bean.");
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		return cacheManager;
	}
	
	
	/**
	 * 利用fastjson转json数据
	 * @param template
	 */
	public void setSerializer(RedisTemplate<Object, Object> template) {
		 @SuppressWarnings({"rawtypes", "unchecked"})
	        FastJsonRedisSerializer<Object> fastJsonRedisSerializer =new FastJsonRedisSerializer<Object>(Object.class);
	        template.setValueSerializer(fastJsonRedisSerializer);
	        template.setHashValueSerializer(fastJsonRedisSerializer);
	        // key的序列化采用StringRedisSerializer
	        template.setKeySerializer(new StringRedisSerializer());
	        template.setHashKeySerializer(new StringRedisSerializer());
	}
	
	/**
	 * 自定义key值的规则
	 */
	@Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                //格式化缓存key字符串
                StringBuilder sb = new StringBuilder();
                //追加类名
                sb.append(o.getClass().getName()).append(".");
                //追加方法名
                sb.append(method.getName());
                //遍历参数并且追加
                for (Object obj : objects) {
                    sb.append(".");
                    sb.append(obj.toString());
                }
                System.out.println("调用Redis缓存Key : " + sb.toString());
                return sb.toString();
            }
        };
	}
	
}
