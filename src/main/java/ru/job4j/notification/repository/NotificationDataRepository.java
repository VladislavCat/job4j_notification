package ru.job4j.notification.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.notification.model.Notification;

public interface NotificationDataRepository
        extends CrudRepository<Notification, Integer> {

}
