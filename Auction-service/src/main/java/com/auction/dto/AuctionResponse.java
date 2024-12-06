package com.auction.dto;

import com.auction.enums.AuctionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionResponse {

    private long itemIdentifier;

    private long sellerIdentifier;

    private Double startPrice;

    private Double reservedPrice;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private AuctionStatus status;
}
