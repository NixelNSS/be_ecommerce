package com.nikolakostic.isa_ecommerce.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    @Getter
    @Setter
    private OrderState state;

    @Generated(GenerationTime.INSERT)
    @Column(name = "date_created")
    @Getter
    @Setter
    private Timestamp dateCreated;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "id"))
    @Getter
    @Setter
    List<Product> products;

    public Order(Double amount, String address, User owner, List<Product> products) {
        this.amount = amount;
        this.address = address;
        this.state = OrderState.IN_PROGRESS;
        this.owner = owner;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) return false;
        Order order = (Order) o;
        return this.getId().equals(order.getId());
    }
}
