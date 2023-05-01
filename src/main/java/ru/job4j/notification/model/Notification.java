package ru.job4j.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "notification_name")
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @Override
    public String toString() {
        return "Notification{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", order=" + order
                + '}';
    }
}
