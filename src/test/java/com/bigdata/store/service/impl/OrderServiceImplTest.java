package com.bigdata.store.service.impl;

import com.bigdata.store.service.OrderService;
import com.bigdata.store.vo.OrderDetailVO;
import com.bigdata.store.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerPhone("1515021034");
        orderDTO.setBuyerAddress("贵州省贵阳市花溪区15号255");
        orderDTO.setSpecsId(1);
        orderDTO.setPhoneQuantity(1);
        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result);
    }
    @Test
    void findDetail(){

    }
}