package com.newflows.publish.demo.controller;

import com.newflows.publish.demo.mq.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("publish")
    public String publish (){
        for(int i=0 ;i<10000;i++){
            //redisTemplate.convertAndSend("MQ_TOPIC_NOTIFY", "我喜欢美女椰蓉+"+i);
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND, 10);
            int laterTime = (int) (cal1.getTimeInMillis() / 1000);
            redisTemplate.opsForZSet().add("MQ_TOPIC_PUBLIS_1","我喜欢美女椰蓉+"+i,laterTime+i);
        }
        return "结束";
    }
}
