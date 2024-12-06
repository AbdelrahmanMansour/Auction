package com.auction.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AuctionStatus {
    ACTIVE("active"),
    COMPLETED("completed"),
    CANCELLED("cancelled"),
    PENDING("pending");

    private final String value;

    AuctionStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AuctionStatus fromValue(String value) {
        for (AuctionStatus status : AuctionStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }

        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
