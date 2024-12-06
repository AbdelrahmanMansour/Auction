package com.auction.exception;

import java.util.function.Supplier;

public class BidNotFoundException extends RuntimeException{

    public BidNotFoundException(String message) {
        super(message);
    }

    public static Supplier<BidNotFoundException> throwBidNotFoundException(Long id) {
        return () -> new BidNotFoundException("Bid with ID " + id + " not found");
    }
}
