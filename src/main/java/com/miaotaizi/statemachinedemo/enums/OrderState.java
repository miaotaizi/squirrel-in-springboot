package com.miaotaizi.statemachinedemo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 实现 IEnum<String> 是为了 Order 的 state 属性在Mybatis中能直接使用此枚举
 */
public enum OrderState implements IEnum<String> {
    UNPAID,     //待支付
    PAID,       //已支付
    DELIVERING,  //配送中
    RECEIVED,    //已签收
    CLOSED;

    @Override
    public String getValue() {
        return toString();
    }
}
