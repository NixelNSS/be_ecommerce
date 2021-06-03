package com.nikolakostic.isa_ecommerce.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_cart_id_seq")
    @SequenceGenerator(name = "shopping_cart_id_seq", allocationSize = 1, initialValue = 3)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Transient
    @Getter
    @Setter
    private Double amount;

    @Transient
    @Getter
    @Setter
    private Integer count;

    @JsonIgnore
    @OneToOne(mappedBy = "shoppingCart")
    @Getter
    @Setter
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shopping_cart_products",
            joinColumns = @JoinColumn(
                    name = "shopping_cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "id"))
    @Getter
    @Setter
    List<Product> products;

    @PostLoad
    public void calculateAmount() {
        this.amount = 0D;
        this.count = 0;
        this.products.forEach(product -> {
            this.amount += product.getPrice();
            this.count++;
        });
    }

    public ShoppingCart(Double amount, Integer count, User owner, List<Product> products) {
        this.amount = amount;
        this.count = count;
        this.owner = owner;
        this.products = products;
    }
}













