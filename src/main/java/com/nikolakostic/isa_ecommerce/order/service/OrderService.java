package com.nikolakostic.isa_ecommerce.order.service;

import com.nikolakostic.isa_ecommerce.order.entity.Order;
import com.nikolakostic.isa_ecommerce.order.entity.OrderState;
import com.nikolakostic.isa_ecommerce.order.exception.InvalidOrderException;
import com.nikolakostic.isa_ecommerce.order.repository.OrderRepository;
import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.shoppingcart.entity.ShoppingCart;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
        orders.forEach(order -> {
            List<Product> products = order.getProducts();
            products.sort(Comparator.comparing(Product::getId));
            order.setProducts(products);
        });
        orders.sort(Comparator.comparing(Order::getAmount));
        return orders;
    }

    public List<Order> getAllByUserAndCriteria(String criteria) {
        return this.getAllByUser().stream()
                .filter(order -> order.getAmount().toString().toLowerCase().contains(criteria.toLowerCase()) ||
                        order.getState().toString().toLowerCase().contains(criteria.toLowerCase()) ||
                        order.getAddress().toLowerCase().contains(criteria.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void create(ShoppingCart shoppingCart, Optional<String> optionalAddress) {
        this.orderRepository.save(new Order(
                shoppingCart.getAmount(),
                optionalAddress.orElse(shoppingCart.getOwner().getAddress()),
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

    public List<Order> receiveById(Long id) throws InvalidOrderException {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent() && optionalOrder.get().getState() == OrderState.IN_PROGRESS &&
                optionalOrder.get().getOwner().getId().equals(this.userService.getAuthenticatedUser().getId())) {
            optionalOrder.get().setState(OrderState.RECEIVED);
            this.orderRepository.save(optionalOrder.get());
            return this.getAllByUser();
        }
        throw new InvalidOrderException();
    }

    public List<Order> cancelById(Long id) throws InvalidOrderException {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent() && optionalOrder.get().getState() == OrderState.IN_PROGRESS &&
                optionalOrder.get().getOwner().getId().equals(this.userService.getAuthenticatedUser().getId())) {
            optionalOrder.get().setState(OrderState.CANCELED);
            this.orderRepository.save(optionalOrder.get());
            return this.getAllByUser();
        }
        throw new InvalidOrderException();
    }

    public Order getById(Long id) throws InvalidOrderException {
        Optional<Order> optionalOrder = this.getAllByUser().stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
        return optionalOrder.orElseThrow(InvalidOrderException::new);
    }

}
