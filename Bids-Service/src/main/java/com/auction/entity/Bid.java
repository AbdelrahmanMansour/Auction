package com.auction.entity;

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
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long auctionId;

    private long bidderId;

    private double amount;

    private LocalDateTime bidTime;

}