package com.auction.service;

import com.auction.dto.BidRequest;
import com.auction.dto.BidResponse;

import java.util.List;
import java.util.Optional;

public interface BidsService {

    Optional<BidResponse> addBid(BidRequest auction);
    Optional<BidResponse> getBid(long bidId);
    Optional<BidResponse> updateBid(long bidId, BidRequest bid);
    boolean deleteBid(Long auctionId);
    List<BidResponse> getAllBids();
}
