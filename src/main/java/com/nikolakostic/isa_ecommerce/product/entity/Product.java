package com.nikolakostic.isa_ecommerce.product.entity;

import com.nikolakostic.isa_ecommerce.category.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

}












