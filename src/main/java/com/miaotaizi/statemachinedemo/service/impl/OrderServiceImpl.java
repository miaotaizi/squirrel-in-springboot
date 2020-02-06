package com.miaotaizi.statemachinedemo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miaotaizi.statemachinedemo.entity.Order;
import com.miaotaizi.statemachinedemo.mapper.OrderMapper;
import com.miaotaizi.statemachinedemo.service.OrderService;
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

}
