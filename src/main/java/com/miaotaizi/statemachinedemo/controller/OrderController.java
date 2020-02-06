package com.miaotaizi.statemachinedemo.controller;

import com.miaotaizi.statemachinedemo.statemachine.OrderStateMachineEngine;
import com.miaotaizi.statemachinedemo.entity.Order;
import com.miaotaizi.statemachinedemo.enums.OrderEvent;
import com.miaotaizi.statemachinedemo.enums.OrderState;
import com.miaotaizi.statemachinedemo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStateMachineEngine orderStateMachineEngine;


    @GetMapping("/{id}")
    public Order getOrder(@PathVariable long id) {
        return orderService.getById(id);
    }

    @PostMapping("")
    public Order createOrder() {
        Order order = new Order();
        order.setState(OrderState.UNPAID);
        orderService.save(order);
        return order;
    }

    @GetMapping("/{id}/paid")
    public Order orderPaid(@PathVariable long id) throws Exception {
        Order order = orderService.getById(id);
        orderStateMachineEngine.fire(order.getState(), OrderEvent.PAY_SUCCESS, order);
        return order;
    }

    @GetMapping("/{id}/refund")
    public Order orderRefund(@PathVariable long id) throws Exception {
        Order order = orderService.getById(id);
        orderStateMachineEngine.fire(order.getState(), OrderEvent.REFUND_SUCCESS, order);
        return order;
    }


}
