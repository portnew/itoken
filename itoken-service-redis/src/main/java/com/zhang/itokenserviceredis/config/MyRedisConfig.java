//package com.zhang.itokenserviceredis.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.net.UnknownHostException;
//
///**
// * @Author: 张宏运
// * @Date: 2019-05-18 16:49
// */
//@Configuration
//public class MyRedisConfig {
//    @Resource
//    private LettuceConnectionFactory myLettuceConnectionFactory;
//
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate() {
//        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.setConnectionFactory(myLettuceConnectionFactory);
//        return template;
//    }
//
//
//}