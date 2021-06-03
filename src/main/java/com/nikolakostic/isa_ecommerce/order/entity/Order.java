package com.nikolakostic.isa_ecommerce.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "amount")
    @Getter
    @Setter
    private Double amount;

    @Column(name = "count")
    @Getter
    @Setter
    private Integer count;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User owner;

    public Order(Double amount, Integer count, User owner) {
        this.amount = amount;
        this.count = count;
        this.owner = owner;
    }

}
