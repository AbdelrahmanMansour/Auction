package com.auction.authorization.exception;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public static Supplier<UserNotFoundException> throwBidNotFoundException(Long id) {
        return () -> new UserNotFoundException("USer with ID " + id + " not found");
    }
}
