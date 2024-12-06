package com.auction.entity;

import com.auction.enums.AuctionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long itemIdentifier;

    private long sellerIdentifier;

    private Double startPrice;

    private Double reservedPrice;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private AuctionStatus status;
}
