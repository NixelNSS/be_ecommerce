package com.nikolakostic.isa_ecommerce.product.entity;

import com.nikolakostic.isa_ecommerce.category.entity.Subcategory;
import com.nikolakostic.isa_ecommerce.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "image_url")
    @Getter
    @Setter
    private String imageURL;

    @Column(name = "price")
    @Getter
    @Setter
    private Double price;

    @Column(name = "seller")
    @Getter
    @Setter
    private String seller;

    @Column(name = "country_of_origin")
    @Getter
    @Setter
    private String countryOfOrigin;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    @Getter
    @Setter
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product")
    @Getter
    @Setter
    private List<Review> reviews;

}












