package com.nikolakostic.isa_ecommerce.shoppingcart.service;

import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.product.service.ProductService;
import com.nikolakostic.isa_ecommerce.shoppingcart.entity.ShoppingCart;
import com.nikolakostic.isa_ecommerce.shoppingcart.repository.ShoppingCartRepository;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    public ShoppingCart addProduct(Long productId) {
        Product product = this.productService.getById(productId);
        ShoppingCart shoppingCart = this.userService.getAuthenticatedUser().getShoppingCart();
        List<Product> products = shoppingCart.getProducts();
        products.add(product);
        shoppingCart.setProducts(products);
        shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        shoppingCart.setCount(products.size());
        shoppingCart.setAmount(shoppingCart.getAmount() + product.getPrice());
        return shoppingCart;
    }

    public ShoppingCart removeProduct(Long productId) {
        this.productService.getById(productId);
        ShoppingCart shoppingCart = this.userService.getAuthenticatedUser().getShoppingCart();
        List<Product> products = shoppingCart.getProducts();
        Optional<Product> optionalProduct = products.stream()
                .filter(current -> current.getId().equals(productId))
                .findFirst();
        if (optionalProduct.isPresent()) {
            products.remove(optionalProduct.get());
            shoppingCart.setProducts(products);
            shoppingCart = this.shoppingCartRepository.save(shoppingCart);
            shoppingCart.setCount(products.size());
            shoppingCart.setAmount(shoppingCart.getAmount() - optionalProduct.get().getPrice());
            return shoppingCart;
        } else throw new RuntimeException("The specified product is not in the cart.");
    }

    public ShoppingCart buy() {
        ShoppingCart shoppingCart = this.userService.getAuthenticatedUser().getShoppingCart();
        shoppingCart.setProducts(new ArrayList<>());
        this.shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart getByUser() {
        return this.userService.getAuthenticatedUser().getShoppingCart();
    }
}
