package com.miaotaizi.statemachinedemo.statemachine;

import com.miaotaizi.statemachinedemo.statemachine.actions.OrderPaySuccessAction;
import com.miaotaizi.statemachinedemo.entity.Order;
import com.miaotaizi.statemachinedemo.enums.OrderEvent;
import com.miaotaizi.statemachinedemo.enums.OrderState;
import com.miaotaizi.statemachinedemo.statemachine.conditions.RandomBooleanCondition;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.squirrelframework.foundation.fsm.StateMachineBuilder;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;


@Service
public class OrderStateMachineEngine implements ApplicationContextAware {

    private StateMachineBuilder<OrderStateMachine, OrderState, OrderEvent, Order> stateMachineBuilder;

    private ApplicationContext applicationContext;

    public OrderStateMachineEngine() {
        //OrderStateMachineEngine 本身为 一个 service, 即间接实现了 stateMachineBuilder 的单例
        stateMachineBuilder = StateMachineBuilderFactory.create(OrderStateMachine.class, OrderState.class, OrderEvent.class, Order.class, ApplicationContext.class);
    }

    /**
     * 配置 stateMachineBuilder
     * 使用这种方式是为了统一管理 action 与 condition 注解的方式会让人感到不安
     */
    protected void configBuilder() {
        stateMachineBuilder.externalTransition()
                .from(OrderState.UNPAID).to(OrderState.PAID).on(OrderEvent.PAY_SUCCESS)
                .when(applicationContext.getBean(RandomBooleanCondition.class))
                .perform(applicationContext.getBean(OrderPaySuccessAction.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        // 实现了 ApplicationContextAware 接口, 可以获取 spring ApplicationContext 上下文
        // 获取到上下文之后, 进行 builder 的配置
        this.configBuilder();
    }

    /**
     * 代理stateMachine 的 fire 使其可以增加 事务 以及 锁的特性
     * 可参考 https://segmentfault.com/a/1190000009906469 文章中 "分布式锁+事务" 章节
     * @param initialState
     * @param trigger
     * @param context
     */
    public void fire(OrderState initialState, OrderEvent trigger, Order context) {
        OrderStateMachine stateMachine = createStateMachine(initialState);
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) applicationContext.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            stateMachine.fire(trigger, context);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    protected OrderStateMachine createStateMachine(OrderState initialState) {
        return stateMachineBuilder.newStateMachine(initialState, applicationContext);
    }
}
