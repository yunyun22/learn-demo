package com.wjq.demo.dt.rocketmq.mq.listener;

import com.alibaba.fastjson.JSON;
import com.lee.entity.Order;
import com.lee.entity.OrderTransactionLog;
import com.lee.service.OrderService;
import com.lee.service.OrderTransactionLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/***
 * @description: 订单成功本地事务
 * @author : 青石路
 * @date: 2021/11/13 10:52
 */
@Component
@Slf4j
public class OrderSuccessTransactionListener implements TransactionListener {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderTransactionLogService orderTransactionLogService;

    /**
     * 执行本地事务
     * @param msg 消息
     * @param arg 本地参数
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("下单开始，执行本地事务开始，订单信息 = {}", JSON.toJSONString(arg));
        try {
            orderService.save((Order) arg, msg.getTransactionId());
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            log.error("下单开始，执行本地事务异常， e = {}", e);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    /**
     * 检查事务（当 Rocket 迟迟未收到本地事务的执行结果是会定时调此方法）
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        log.info("查询本地事务执行状态，msg = {}", JSON.toJSONString(new String (msg.getBody())));
        OrderTransactionLog orderTransactionLog = orderTransactionLogService.getById(msg.getMsgId());
        if (Objects.isNull(orderTransactionLog)) {
            return LocalTransactionState.UNKNOW;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
