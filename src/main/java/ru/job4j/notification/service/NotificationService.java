package ru.job4j.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.notification.model.Notification;
import ru.job4j.notification.model.Order;
import ru.job4j.notification.repository.NotificationDataRepository;
import ru.job4j.notification.repository.OrderDataRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationDataRepository ndr;
    private final OrderDataRepository odr;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "job4j_orders")
    public void receiveOrder(Order order) {
        Notification notification = new Notification();
        odr.save(order);
        notification.setName("Уведомление о создании заказа №" + order.getId());
        notification.setDescription(order.getDescriptionOrder());
        notification.setOrder(order);
        ndr.save(notification);
        log.info(notification.toString());
    }
}