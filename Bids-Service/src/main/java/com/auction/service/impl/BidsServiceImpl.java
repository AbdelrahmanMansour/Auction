package com.auction.service.impl;

import com.auction.dto.BidRequest;
import com.auction.dto.BidResponse;
import com.auction.entity.Bid;
import com.auction.exception.BidNotFoundException;
import com.auction.repository.BidsRepository;
import com.auction.service.BidsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BidsServiceImpl implements BidsService {

    BidsRepository bidsRepository;

    ModelMapper modelMapper;


    @Override
    public Optional<BidResponse> addBid(BidRequest auction) {
        try {
            Bid response = bidsRepository.save(modelMapper.map(auction, Bid.class));
            return Optional.of(modelMapper.map(response, BidResponse.class));
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to save Bid due to constraint violation", ex);
        }
    }

    @Override
    public Optional<BidResponse> getBid(long bidId) {
        Bid response = bidsRepository.findById(bidId)
                .orElseThrow(BidNotFoundException.throwBidNotFoundException(bidId));
        return Optional.of(modelMapper.map(response, BidResponse.class));    }

    @Override
    public Optional<BidResponse> updateBid(long bidId, BidRequest bidRequest) {
        Bid updatedAuction = bidsRepository.findById(bidId)
                .map(bid -> {
                    bid.setAuctionIdentifier(bidRequest .getAuctionIdentifier());
                    bid.setBidTime(bidRequest.getBidTime());
                    bid.setAmount(bidRequest.getAmount());
                    bid.setBidderIdentifier(bidRequest.getBidderIdentifier());
                    return bidsRepository.save(bid);
                }).orElseThrow(BidNotFoundException.throwBidNotFoundException(bidId));
        return Optional.of(modelMapper.map(updatedAuction, BidResponse.class));
    }

    @Override
    public boolean deleteBid(Long auctionId) {
        bidsRepository.findById(auctionId)
                .map(auction -> {
                    bidsRepository.deleteById(auctionId);
                    return true;
                });
        return false;
    }

    @Override
    public List<BidResponse> getAllBids() {
        return bidsRepository.findAll()
                .stream()
                .map(bid -> modelMapper.map(bid, BidResponse.class))
                .toList();
    }
}

