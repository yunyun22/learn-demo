package com.wjq.demo.dt.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/***
 * @description: 订单
 * @author : 青石路
 * @date: 2021/11/13 14:43
 */
@Setter
@Getter
@TableName("t_order")
public class Order {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 下单金额
     */
    private BigDecimal orderAmount;

    /**
     * 备注
     */
    private String note;
}
