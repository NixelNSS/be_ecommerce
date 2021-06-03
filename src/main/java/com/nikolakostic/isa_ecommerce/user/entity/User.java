package com.nikolakostic.isa_ecommerce.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolakostic.isa_ecommerce.category.entity.Category;
import com.nikolakostic.isa_ecommerce.order.entity.Order;
import com.nikolakostic.isa_ecommerce.shoppingcart.entity.ShoppingCart;
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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1, initialValue = 3)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @JsonIgnore
    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name = "phone")
    @Getter
    @Setter
    private String phone;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_favorite_categories",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id", referencedColumnName = "id"))
    @Getter
    @Setter
    private List<Category> favoriteCategories;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @JsonIgnore
    @Getter
    @Setter
    @OneToMany(mappedBy = "owner")
    private List<Order> orders;

    @Generated(GenerationTime.INSERT)
    @Column(name = "date_created")
    @Getter
    @Setter
    private Timestamp dateCreated;

    public User(String firstName, String lastName, String email, String password, String phone, String address, List<Category> favoriteCategories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.favoriteCategories = favoriteCategories;
    }
}
