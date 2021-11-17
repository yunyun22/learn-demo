package com.wjq.demo.dt.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/***
 * @description: 订单事务日志
 * @author : 青石路
 * @date: 2021/11/14 9:03
 */
@Setter
@Getter
@TableName("t_order_transaction_log")
public class OrderTransactionLog {

    /**
     * 事务id
     */
    @TableId(type = IdType.INPUT)
    private String transactionId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 备注
     */
    private String note;
}
