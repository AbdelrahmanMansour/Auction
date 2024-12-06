package com.auction.service.impl;

import com.auction.dto.AuctionRequest;
import com.auction.dto.AuctionResponse;
import com.auction.entity.Auction;
import com.auction.exception.AuctionNotFoundException;
import com.auction.repository.AuctionRepository;
import com.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    AuctionRepository auctionRepository;

    ModelMapper modelMapper;

    @Override
    public Optional<AuctionResponse> addAuction(AuctionRequest auction) {
        try {
            Auction response = auctionRepository.save(modelMapper.map(auction, Auction.class));
            return Optional.of(modelMapper.map(response, AuctionResponse.class));
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to save item due to constraint violation", ex);
        }
    }

    @Override
    public Optional<AuctionResponse> getAuction(long auctionId) {
        Auction response = auctionRepository.findById(auctionId)
                .orElseThrow(AuctionNotFoundException.throwItemNotFoundException(auctionId));
        return Optional.of(modelMapper.map(response, AuctionResponse.class));
    }

    @Override
    public Optional<AuctionResponse> updateAuction(long auctionId, AuctionRequest auctionRequest) {
        Auction updatedAuction = auctionRepository.findById(auctionId)
                .map(auction -> {
                    auction.setItemIdentifier(auctionRequest.getItemIdentifier());
                    auction.setStatus(auctionRequest.getStatus());
                    auction.setStartTime(auctionRequest.getStartTime());
                    auction.setEndTime(auctionRequest.getEndTime());
                    auction.setSellerIdentifier(auctionRequest.getSellerIdentifier());
                    return auctionRepository.save(auction);
                }).orElseThrow(AuctionNotFoundException.throwItemNotFoundException(auctionId));
        return Optional.of(modelMapper.map(updatedAuction, AuctionResponse.class));
    }

    @Override
    public boolean deleteAuction(Long auctionId) {
        auctionRepository.findById(auctionId)
                .map(auction -> {
                    auctionRepository.deleteById(auctionId);
                    return true;
                });
        return false;
    }

    @Override
    public List<AuctionResponse> getAllAuctions() {
        return auctionRepository.findAll()
                .stream()
                .map(auction -> modelMapper.map(auction, AuctionResponse.class))
                .toList();
    }
}
