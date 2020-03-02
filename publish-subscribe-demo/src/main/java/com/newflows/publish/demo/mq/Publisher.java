package com.newflows.publish.demo.mq;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * ClassName:@Publisher
 * Description:消息队列生产者
 *
 * @ Aauthor:caigs
 * @ 498.net
 * @ Version V1.0
 **/
@Component("mqPublisher")
public class Publisher {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 发送消息
     * @param topic
     * @param msg
     * @return
     */
    public boolean sendMessage(String topic,String msg) {
        System.out.println("publisher msg,topic:" + topic + ",msg:" + msg);
        try {
            redisTemplate.convertAndSend(topic, msg);
            return true;
        } catch (Exception e) {
            System.out.println("publisher msg,error:" + e.toString() );
            return false;
        }
    }

}
