package com.mazlumemre.notcum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mazlumemre.notcum.entity.Order;
import com.mazlumemre.notcum.entity.Stationery;
import com.mazlumemre.notcum.entity.User;
import com.mazlumemre.notcum.repository.StationeryRepository;
import com.mazlumemre.notcum.service.OrderService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StationeryRepository stationeryRepository;

    @PostMapping("/upload")
    public ResponseEntity<Order> createOrder(
            @RequestParam String courseName,
            @RequestParam String price,

            @RequestParam Long stationeryId,
            HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");

        // ID ile Stationery nesnesini alın
        Stationery stationery = stationeryRepository.findById(stationeryId)
                .orElseThrow(() -> new IllegalArgumentException("Kırtasiye bulunamadı."));

        // Order nesnesi oluştur
        Order order = new Order();
        order.setTitle(courseName);
        order.setPrice(price);
        order.setOwner(loggedUser.getName());
        order.setStationery(stationery);

        // Kaydedip yanıt gönder
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(savedOrder);
    }


    @GetMapping("/stationery/{stationeryId}")
    public ResponseEntity<List<Order>> getOrdersByStationeryId(@PathVariable("stationeryId") Long stationeryId) {
        List<Order> orders = orderService.getOrdersByStationeryId(stationeryId);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }


}
