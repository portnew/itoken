package com.zhang.itokenserviceredis;

import com.zhang.itokenserviceredis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItokenServiceRedisApplicationTests {

    @Autowired
    RestTemplate template;
    @Autowired
    RedisService service;
    @Test
    public void contextLoads() {

    }

}
