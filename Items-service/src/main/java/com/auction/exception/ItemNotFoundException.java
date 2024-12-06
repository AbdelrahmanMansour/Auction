package com.auction.exception;


import java.util.function.Supplier;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }

    public static Supplier<ItemNotFoundException> throwItemNotFoundException(Long id) {
        return () -> new ItemNotFoundException("Item with ID " + id + " not found");
    }
}
