package ru.job4j.notification.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.notification.model.Order;

public interface OrderDataRepository extends CrudRepository<Order, Integer> {
}
