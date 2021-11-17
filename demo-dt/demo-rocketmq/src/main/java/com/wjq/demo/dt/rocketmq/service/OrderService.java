package com.wjq.demo.dt.rocketmq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjq.demo.dt.rocketmq.entity.Order;

/***
 * @description: Order service
 * @author : 青石路
 * @date: 2021/11/13 14:40
 */
public interface OrderService extends IService<Order> {

    /**
     * 下单
     * @param order
     * @return
     */
    Order add(Order order);

    /**
     * 订单数据落库
     * @param order
     * @param transactionId
     */
    void save(Order order, String transactionId);
}
