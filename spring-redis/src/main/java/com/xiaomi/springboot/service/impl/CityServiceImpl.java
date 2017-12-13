package com.xiaomi.springboot.service.impl;

import com.xiaomi.springboot.dao.CityDao;
import com.xiaomi.springboot.domain.City;
import com.xiaomi.springboot.service.CityService;
import com.xiaomi.springboot.utils.JedisConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaolai on 2017/11/29.
 */
@Service
public class CityServiceImpl implements CityService {

    private final  static Logger LOGGER= LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private JedisConfiguration jedisConfiguration;


    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public City findCityById(Long id) {
        Jedis resource = jedisConfiguration.redisPoolFactory().getResource();
        String key= "city_" + id;
        City city = cityDao.findById(id);
        resource.set(key,JSON.toJSONString(city));
        return null;
      /*  ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        Boolean aBoolean = redisTemplate.hasKey(key);
        if (aBoolean){
            String city = valueOperations.get(key);
            City city1 = JSON.parseObject(city, City.class);
            return city1;
        }else{
            City city = cityDao.findById(id);
            valueOperations.set(key, JSON.toJSONString(city),1000, TimeUnit.SECONDS);
            LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
            return city;
        }*/
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);

        // 缓存存在，删除缓存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }

        return ret;
    }

    @Override
    public Long deleteCity(Long id) {
        Long ret = cityDao.deleteCity(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        return ret;
    }


    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
       /* redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);*/
        this.redisTemplate = redisTemplate;
    }
}
