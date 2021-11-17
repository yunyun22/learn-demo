package com.wjq.demo.dt.rocketmq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.entity.Order;
import com.lee.entity.OrderTransactionLog;
import com.lee.mapper.OrderMapper;
import com.lee.mq.producer.OrderSuccessProducer;
import com.lee.service.OrderService;
import com.lee.service.OrderTransactionLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/***
 * @description: Order service impl
 * @author : 青石路
 * @date: 2021/11/13 14:41
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderTransactionLogService orderTransactionLogService;

    @Resource
    private OrderSuccessProducer orderSuccessProducer;

    @Override
    public Order add(Order order) {
        // 1、相关数据查询、数据校验

        // 2、订单号生成（20位）
        order.setOrderNo(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + "123");

        // 3、发送事务消息
        try {
            TransactionSendResult transactionSendResult = orderSuccessProducer.sendTxMsg(order, order);
            if (SendStatus.SEND_OK.equals(transactionSendResult.getSendStatus())) {
                return order;
            }
        } catch (Exception e) {
            log.error("下单成功，事务消息发送异常，e={}", e);
            throw new RuntimeException("下单成功，事务消息发送异常");
        }
        throw new RuntimeException("消息发送未成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Order order, String transactionId) {
        // 1、订单数据落库
        this.save(order);

        // 2、订单事务日志落库
        OrderTransactionLog orderTransactionLog = new OrderTransactionLog();
        orderTransactionLog.setTransactionId(transactionId);
        orderTransactionLog.setOrderId(order.getOrderId());
        orderTransactionLog.setNote(order.getNote());
        orderTransactionLogService.save(orderTransactionLog);
        log.info("订单添加成功");
    }
}
