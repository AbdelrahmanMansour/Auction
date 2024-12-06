package com.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidResponse {

    private long auctionIdentifier;

    private long bidderIdentifier;

    private double amount;

    private LocalDateTime bidTime;
}
