package com.wjq.demo.dt.rocketmq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.entity.OrderTransactionLog;
import org.apache.ibatis.annotations.Mapper;

/***
 * @description: 订单事务日志
 * @author : 青石路
 * @date: 2021/11/14 9:26
 */
@Mapper
public interface OrderTransactionLogMapper extends BaseMapper<OrderTransactionLog> {
}
