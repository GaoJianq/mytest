package com.newflows.publish.demo.config;

import com.newflows.publish.demo.mq.Receiver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@AutoConfigureAfter({Receiver.class})//主要是控制类的加载顺序,即 指定的类加载完了,再加载本类
public class SubscriberConfig {

    /**
     * 创建消息监听容器
     * 监听推送的广播 messageListenerAdapter 注入接受消息方法名
     *
     * @param redisConnectionFactory
     * @param messageListenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(@Qualifier("cachefactory") RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("MQ_TOPIC_NOTIFY"));
        return redisMessageListenerContainer;
    }

    /**
     * 消息监听适配器，注入接受消息方法，输入方法名字 推送数据
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver);
    }

}
