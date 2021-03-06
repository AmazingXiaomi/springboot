package com.xiaomi.springboot.redis;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.lang.reflect.Method;

@Configuration
@EnableCaching//启用缓存，这个注解很重要；
//继承CachingConfigurerSupport，为了自定义生成KEY的策略。可以不继承。
public class RedisConfig extends CachingConfigurerSupport{

    //因为生产环境和开发环境使用不同的启动资源文件，所以使用了@Profile，用来指定使用的启动资源文件
    @Configuration
    @Profile(value={"dev"})//如果你不需要的话可以删掉
    static class LocalConfiguration {
        //从application.properties中获得以下参数
        @Value("${spring.redis.host}")
        private String host;
        @Value("${spring.redis.port}")
        private Integer port;
        @Value("${spring.redis.password}")
        private String password;

        /**
         * 缓存管理器.
         * @param redisTemplate
         * @return
         */
        @Bean
        public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
            CacheManager cacheManager = new RedisCacheManager(redisTemplate);
            return cacheManager;
        }
        @Bean
        public RedisTemplate<Serializable, Serializable> redisTemplate(
                JedisConnectionFactory redisConnectionFactory) {

            RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<Serializable, Serializable>();
            //key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
            //所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现 ObjectRedisSerializer
            //或者JdkSerializationRedisSerializer序列化方式;
         //   redisTemplate.setHashKeySerializer(new StringRedisSerializer());
           /* RedisSerializer stringSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(stringSerializer);*/

            //  redisTemplate.setKeySerializer(stringSerializer);
            //redisTemplate .setValueSerializer(stringSerializer);
          //  redisTemplate .setHashValueSerializer(new JdkSerializationRedisSerializer());
            //以上4条配置可以不用
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            return redisTemplate;
        }

        @Bean
        public JedisConnectionFactory redisConnectionFactory() {
            JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
            redisConnectionFactory.setHostName(host);
            redisConnectionFactory.setPort(port);
            redisConnectionFactory.setPassword(password);

            return redisConnectionFactory;
        }
    }

    /**
     * 自定义key.
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects){
            StringBuilder sb = new StringBuilder();
              sb.append(o.getClass().getName());
              sb.append(method.getName());
              for (Object obj : objects) {
                sb.append(obj.toString());
            }
              return sb.toString();
        }
    };
}
}