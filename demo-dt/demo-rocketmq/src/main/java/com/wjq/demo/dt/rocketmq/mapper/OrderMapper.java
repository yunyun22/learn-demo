package com.wjq.demo.dt.rocketmq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.wjq.demo.dt.rocketmq.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/***
 * @description: order mapper
 * @author : 青石路
 * @date: 2021/11/13 16:13
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
