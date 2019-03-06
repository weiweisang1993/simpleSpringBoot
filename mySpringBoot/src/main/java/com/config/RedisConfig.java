package com.config;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.util.RedisUtil;


/**
 * 如果我们只需要整合redis的单机版，只要在redis.conf中进行如下配置
 * @author 炜哥
 *
 */
@Configuration
@EnableCaching
public class RedisConfig{
	
//	/**
//	 * Redis数据库索引（默认为0）
//	 */
//	@Value("${spring.redis.database}")
//	private Integer database;
//	
//	/**
//	 * Redis服务器地址
//	 */
//	@Value("${spring.redis.host}")
//	private String host;
//	
//	/**
//	 * Redis服务器连接端口
//	 */
//	@Value("${spring.redis.port}")
//	private Integer port;
//	
//	/**
//	 * Redis服务器连接密码（默认为空）
//	 */
//	@Value("${spring.redis.password}")
//	private String password;
//	
//	/**
//	 *  连接池最大连接数（使用负值表示没有限制）
//	 */
//	@Value("${spring.redis.pool.max-active}")
//	private Integer maxActive;
//	
//	/**
//	 * 连接池最大阻塞等待时间（使用负值表示没有限制）
//	 */
//	@Value("${spring.redis.pool.max-wait}")
//	private Integer maxWaitMillis;
//	
//	/**
//	 * 连接池中的最大空闲连接
//	 */
//	@Value("${spring.redis.pool.max-idle}")
//	private Integer maxIdle;
//	
//	/**
//	 * 连接池中的最小空闲连接
//	 */
//	@Value("${spring.redis.pool.min-idle}")
//	private Integer minIdle;
//	
//	/**
//	 * 连接超时时间（毫秒）
//	 */
//	@Value("${spring.redis.timeout}")
//	private Integer timeout;
//	
//	/**
//	 * redis连接池配置
//	 * @return
//	 */
//	@Bean
//	public Jedis jedisFactory(){
//		
//		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//		jedisPoolConfig.setMaxIdle(maxIdle);
//		jedisPoolConfig.setMinIdle(minIdle);
//		jedisPoolConfig.setMaxTotal(maxActive);
//		jedisPoolConfig.setBlockWhenExhausted(true);
//		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//		
//		JedisPool jedisPool = null;
//		if("".equals(password)){
//			jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//		}else{
//			jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
//		}
//		Jedis jedis = jedisPool.getResource();
//		jedisPool.close();
//		
//		return jedis;
//	}
//	
//	@Bean
//	public JedisPool jedisPoolFactory(){
//		
//		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//		jedisPoolConfig.setMaxIdle(maxIdle);
//		jedisPoolConfig.setMinIdle(minIdle);
//		jedisPoolConfig.setMaxTotal(maxActive);
//		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//		
//		JedisPool jedisPool = null;
//		if("".equals(password)){
//			jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//		}else{
//			jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
//		}
//		
//		return jedisPool;
//	}
	
	/**
	 * 实例化RedisTemplate
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public <T> RedisTemplate<String, T> redisTemplateKeyString(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
	
	/**配置其他类型的redisTemplate***/
    @Bean
    public RedisTemplate<Object, Object> redisTemplateKeyObject(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
	
	
	/**
	 * 封装RedisTemplate
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil(redisTemplate);
        return redisUtil;
    }
	
	/**
	 * 自定义keyGenerator必须实现org.springframework.cache.interceptor.KeyGenerator接口
	 * @return
	 */
	@Bean
	public KeyGenerator accountKeyGenerator() {
		return new KeyGenerator(){
			@Override
			public Object generate(Object target, Method method, Object... params) {
				return target.getClass().toString() + "accountId:" + params[0].toString();
			}
		};
	}
}
