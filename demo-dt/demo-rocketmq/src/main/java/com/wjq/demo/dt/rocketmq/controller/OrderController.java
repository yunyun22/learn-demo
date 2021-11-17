package com.wjq.demo.dt.rocketmq.controller;


import com.wjq.demo.dt.rocketmq.entity.Order;
import com.wjq.demo.dt.rocketmq.service.OrderService;
import com.wjq.demo.dt.rocketmq.vo.OrderVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @description: order controller
 * @author : 青石路
 * @date: 2021/11/13 14:38
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    public Order add(@RequestBody OrderVo orderVo) {
        return orderService.add(orderVo);
    }

    @GetMapping("/{orderId}")
    public Order order(@PathVariable Long orderId) {
        return orderService.getById(orderId);
    }
}
