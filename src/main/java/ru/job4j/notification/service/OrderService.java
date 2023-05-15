package ru.job4j.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.notification.model.Order;
import ru.job4j.notification.repository.OrderDataRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDataRepository odr;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<Object, Order> kafkaTemplate;

    public void sendOrder(int id) {
        Optional<Order> opt = odr.findById(id);
        if (opt.isEmpty()) {
            return;
        }
        Order order = setStatus(opt.get());
        kafkaTemplate.send("cooked_order", order);
    }

    public Order setStatus(Order order) {
        double d = Math.random() * 10;
        if (d <= 6) {
            order.setStatusOrder("Заказ готов");
        } else if (d > 6) {
            order.setStatusOrder("На кухне возникли проблемы");
        }
        return order;
    }
 }
