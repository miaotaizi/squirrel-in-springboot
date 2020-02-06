package com.miaotaizi.statemachinedemo.statemachine.conditions;

import com.miaotaizi.statemachinedemo.entity.Order;

import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.AnonymousCondition;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RandomBooleanCondition extends AnonymousCondition<Order> {
    @Override
    public boolean isSatisfied(Order order) {
        boolean res = new Random().nextBoolean();
        log.info("RandomBooleanCondition result: {} ", res);
        return res;
    }
}
