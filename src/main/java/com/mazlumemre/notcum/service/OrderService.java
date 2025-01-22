package com.mazlumemre.notcum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mazlumemre.notcum.entity.Order;
import com.mazlumemre.notcum.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByStationeryId(Long stationeryId) {
        return orderRepository.findByStationeryId(stationeryId);
    }


}
