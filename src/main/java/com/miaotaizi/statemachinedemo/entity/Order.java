package com.miaotaizi.statemachinedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.miaotaizi.statemachinedemo.enums.OrderState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_order")
public class Order {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "state")
    private OrderState state;

    public static final String COL_ID = "id";

    public static final String COL_STATE = "state";

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }
}