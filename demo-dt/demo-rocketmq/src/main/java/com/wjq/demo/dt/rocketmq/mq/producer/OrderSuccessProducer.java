package com.wjq.demo.dt.rocketmq.mq.producer;

import com.alibaba.fastjson.JSON;
import com.lee.mq.listener.OrderSuccessTransactionListener;
import lombok.Setter;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/***
 * @description: 订单下单成功 producer
 * @author : 青石路
 * @date: 2021/11/13 10:23
 */
@Component
@ConfigurationProperties(prefix = "rocket")
@Setter
public class OrderSuccessProducer implements InitializingBean, DisposableBean {

    private String nameSrvAddr;
    private String orderGroupName;
    private String pointsTopic;
    private String pointsTag;

    @Resource
    private OrderSuccessTransactionListener orderSuccessTransactionListener;
    @Resource
    private ThreadPoolExecutor rocketThreadPoolExecutor;

    private TransactionMQProducer producer;

    @Override
    public void afterPropertiesSet() throws Exception {
        producer = new TransactionMQProducer(orderGroupName);
        producer.setNamesrvAddr(nameSrvAddr);
        producer.setSendMsgTimeout(Integer.MAX_VALUE);
        producer.setExecutorService(rocketThreadPoolExecutor);
        producer.setTransactionListener(orderSuccessTransactionListener);
        producer.start();
        Message message = new Message(pointsTopic, pointsTag, "test".getBytes());
        producer.send(message);
    }

    @Override
    public void destroy() throws Exception {
        if (Objects.nonNull(producer)) {
            producer.shutdown();
        }
    }

    /**
     *  发送半事务消息
     * @param msg 消息对象
     * @param arg 本地事务参数
     * @return
     * @throws MQClientException
     */
    public TransactionSendResult sendTxMsg(Object msg, Object arg) throws MQClientException {
        Message message = new Message(pointsTopic, pointsTag, JSON.toJSONString(msg).getBytes());
        return this.producer.sendMessageInTransaction(message, arg);
    }
}
