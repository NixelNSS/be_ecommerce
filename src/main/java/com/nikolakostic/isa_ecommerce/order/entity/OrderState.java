package com.nikolakostic.isa_ecommerce.order.entity;

public enum OrderState {
    IN_PROGRESS("In progress"),
    CANCELED("Canceled"),
    RECEIVED("Received");

    private String name;

    OrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}