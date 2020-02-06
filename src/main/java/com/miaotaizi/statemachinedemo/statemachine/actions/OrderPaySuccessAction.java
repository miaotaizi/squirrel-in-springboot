package com.miaotaizi.statemachinedemo.statemachine.actions;

import com.miaotaizi.statemachinedemo.entity.Order;
import com.miaotaizi.statemachinedemo.enums.OrderEvent;
import com.miaotaizi.statemachinedemo.enums.OrderState;
import com.miaotaizi.statemachinedemo.service.OrderService;
import com.miaotaizi.statemachinedemo.statemachine.OrderStateMachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.AnonymousAction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderPaySuccessAction extends AnonymousAction<OrderStateMachine, OrderState, OrderEvent, Order> {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(OrderState orderState, OrderState s1, OrderEvent event, Order order, OrderStateMachine orderStateMachine) {
        Order nextOrder = orderService.getById(order.getId()+1);
        log.info("next order info: {}", nextOrder);
        log.info("executed by class: {}", OrderPaySuccessAction.class.toString());
    }
}
