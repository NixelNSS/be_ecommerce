package com.nikolakostic.isa_ecommerce.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolakostic.isa_ecommerce.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subcategories")
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Getter
    @Setter
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "subcategory")
    @Getter
    @Setter
    private List<Product> products;

}
