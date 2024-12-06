package com.auction.exception;

import java.util.function.Supplier;

public class AuctionNotFoundException extends RuntimeException{

    public AuctionNotFoundException(String message) {
        super(message);
    }

    public static Supplier<AuctionNotFoundException> throwItemNotFoundException(Long id) {
        return () -> new AuctionNotFoundException("Auction with ID " + id + " not found");
    }
}
