package com.nikolakostic.isa_ecommerce.shoppingcart.service;

import com.nikolakostic.isa_ecommerce.order.service.OrderService;
import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.product.service.ProductService;
import com.nikolakostic.isa_ecommerce.shoppingcart.dto.ProductCountDTO;
import com.nikolakostic.isa_ecommerce.shoppingcart.dto.ShoppingCartReturnDTO;
import com.nikolakostic.isa_ecommerce.shoppingcart.entity.ShoppingCart;
import com.nikolakostic.isa_ecommerce.shoppingcart.repository.ShoppingCartRepository;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    private List<ProductCountDTO> refactorProducts(List<Product> products) {
        Map<Product, Long> map = products.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        List<ProductCountDTO> list = new ArrayList<>();
        map.forEach((product, count) -> list.add(new ProductCountDTO(product, count)));
        list.sort(Comparator.comparing(p -> p.getProduct().getName()));
        return list;
    }

    private ShoppingCartReturnDTO mapToReturnDTO(ShoppingCart shoppingCart) {
        return new ShoppingCartReturnDTO(
                shoppingCart.getId(),
                shoppingCart.getAmount(),
                shoppingCart.getCount(),
                refactorProducts(shoppingCart.getProducts())
        );
    }

    public ShoppingCartReturnDTO addProduct(Long productId) {
        Product product = this.productService.getById(productId);
        ShoppingCart shoppingCart = this.userService.getAuthenticatedUser().getShoppingCart();
        List<Product> products = shoppingCart.getProducts();
        products.add(product);
        shoppingCart.setProducts(products);
        shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        shoppingCart.setCount((long) products.size());
        shoppingCart.setAmount(shoppingCart.getAmount() + product.getPrice());
        return this.mapToReturnDTO(shoppingCart);
    }

    public ShoppingCartReturnDTO decreaseProduct(Long productId) {
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
            shoppingCart.setCount((long) products.size());
            shoppingCart.setAmount(shoppingCart.getAmount() - optionalProduct.get().getPrice());
            return this.mapToReturnDTO(shoppingCart);
        } else throw new RuntimeException("The specified product is not in the cart.");
    }

    public ShoppingCartReturnDTO removeProduct(Long productId) {
        Product selected = this.productService.getById(productId);
        ShoppingCart shoppingCart = this.userService.getAuthenticatedUser().getShoppingCart();
        List<Product> products = shoppingCart.getProducts();
        int size = products.size();
        List<Product> filtered = products.stream()
                .filter(product -> product.getId().equals(productId))
                .collect(Collectors.toList());
        products.removeAll(filtered);
        if (filtered.size() == 0) throw new RuntimeException("The specified product is not in the cart.");
        shoppingCart.setProducts(products);
        shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        shoppingCart.setCount((long) products.size());
        shoppingCart.setAmount(shoppingCart.getAmount() - (selected.getPrice() * filtered.size()));
        return this.mapToReturnDTO(shoppingCart);
    }

    public ShoppingCartReturnDTO buy() {
        ShoppingCart shoppingCart = this.userService.getAuthenticatedUser().getShoppingCart();
        this.orderService.create(shoppingCart);
        shoppingCart.setProducts(new ArrayList<>());
        this.shoppingCartRepository.save(shoppingCart);
        shoppingCart.setCount(0L);
        shoppingCart.setAmount(0D);
        return this.mapToReturnDTO(shoppingCart);
    }

    public ShoppingCartReturnDTO getByUser() {
        return this.mapToReturnDTO(this.userService.getAuthenticatedUser().getShoppingCart());
    }

    public ShoppingCart create(ShoppingCart shoppingCart) {
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
