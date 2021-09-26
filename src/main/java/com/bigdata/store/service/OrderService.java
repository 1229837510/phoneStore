package com.bigdata.store.service;

import com.bigdata.store.vo.OrderDetailVO;
import com.bigdata.store.dto.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    OrderDetailVO findOrderDetailByOrderId(String orderId);
    String pay(String orderId);
}
