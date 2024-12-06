package com.auction.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidRequest {

    private long auctionId;

    private long bidderId;

    private double amount;

    private LocalDateTime bidTime;

}
