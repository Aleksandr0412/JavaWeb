package com.aleksandr0412.springdata.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@ToString
public class Order {
    public enum OrderStatus {
        IN_PROCESSING("В обработке"),
        READY("Готово");

        private final String statusName;

        OrderStatus(String statusName) {
            this.statusName = statusName;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems;

}