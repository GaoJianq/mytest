package com.newflows.publish.demo.mq;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * ClassName:@Publisher
 * Description:消息队列消费者 用户推送 监听处理业务
 **/
@Component
public class Receiver implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("频道：MQ_TOPIC_NOTIFY start....");
        logger.info("进入监听mq消息队列==》推送message：" + message.toString());
        //message判空
        String json = new Gson().toJson(message);
        if(StringUtils.isEmpty(json)){
            logger.info("参数不能为空,取消推送");
            return;
        }
    }
}
