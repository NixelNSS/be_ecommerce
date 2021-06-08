package com.nikolakostic.isa_ecommerce.order.service;

import com.nikolakostic.isa_ecommerce.order.entity.Order;
import com.nikolakostic.isa_ecommerce.order.exception.InvalidOrderException;
import com.nikolakostic.isa_ecommerce.order.repository.OrderRepository;
import com.nikolakostic.isa_ecommerce.shoppingcart.entity.ShoppingCart;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    public List<Order> getAllByUser() {
        List<Order> orders = this.userService.getAuthenticatedUser().getOrders();
        orders.forEach(order -> order.setProducts(order.getProducts().stream()
                                                                     .distinct()
                                                                     .collect(Collectors.toList())));
        return orders;
    }

    public void create(ShoppingCart shoppingCart) {
        this.orderRepository.save(new Order(
                shoppingCart.getAmount(),
                shoppingCart.getOwner().getAddress(),
                shoppingCart.getOwner(),
                new ArrayList<>(shoppingCart.getProducts())
        ));
    }

    public List<Order> deleteById(Long id) throws InvalidOrderException {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent() &&
            optionalOrder.get().getOwner().getId().equals(this.userService.getAuthenticatedUser().getId())) {
            this.orderRepository.delete(optionalOrder.get());
            return this.getAllByUser();
        }
        throw new InvalidOrderException();
    }

}
