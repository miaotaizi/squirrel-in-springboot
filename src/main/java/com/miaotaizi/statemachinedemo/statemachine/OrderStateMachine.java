package com.miaotaizi.statemachinedemo.statemachine;

import com.miaotaizi.statemachinedemo.entity.Order;
import com.miaotaizi.statemachinedemo.enums.OrderEvent;
import com.miaotaizi.statemachinedemo.enums.OrderState;
import com.miaotaizi.statemachinedemo.service.OrderService;

import org.springframework.context.ApplicationContext;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderStateMachine extends AbstractStateMachine<OrderStateMachine, OrderState, OrderEvent, Order> {

    private ApplicationContext applicationContext;

    public OrderStateMachine(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void afterTransitionCompleted(OrderState fromState, OrderState toState, OrderEvent event, Order order) {
            order.setState(toState);
            OrderService orderService = applicationContext.getBean(OrderService.class);
            orderService.updateById(order);
    }
}
