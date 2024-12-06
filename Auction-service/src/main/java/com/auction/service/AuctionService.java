package com.auction.service;

import com.auction.dto.AuctionRequest;
import com.auction.dto.AuctionResponse;
import com.auction.entity.Auction;

import java.util.List;
import java.util.Optional;

public interface AuctionService {

    Optional<AuctionResponse> addAuction(AuctionRequest auction);
    Optional<AuctionResponse> getAuction(long auctionId);
    Optional<AuctionResponse> updateAuction(long auctionId, AuctionRequest auction);
    boolean deleteAuction(Long auctionId);
    List<AuctionResponse> getAllAuctions();
}
