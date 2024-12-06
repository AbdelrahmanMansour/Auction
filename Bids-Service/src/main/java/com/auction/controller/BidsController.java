package com.auction.controller;

import com.auction.dto.BidRequest;
import com.auction.dto.BidResponse;
import com.auction.service.BidsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bid")
@AllArgsConstructor
public class BidsController {

    BidsService bidsService;

    @PostMapping("/addBid")
    public ResponseEntity<String> addBid(@RequestBody BidRequest bidRequest) {
        bidsService.addBid(bidRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bids created successfully");
    }

    @GetMapping("/getBid")
    public ResponseEntity<Optional<BidResponse>> getBid(@RequestParam ("id") Long id) {
        Optional<BidResponse> response = bidsService.getBid(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllBids")
    public ResponseEntity<List<BidResponse>> getAllBids() {
        return ResponseEntity.ok(bidsService.getAllBids());
    }

    @PutMapping("/updateBid")
    public ResponseEntity<Optional<BidResponse>> updateBid(@RequestParam ("id") Long id, @RequestBody BidRequest bidRequest) {
        Optional<BidResponse> response = bidsService.updateBid(id, bidRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteBid")
    public ResponseEntity<Optional<BidResponse>> deleteBid(@RequestParam ("id") Long id) {
        if(bidsService.deleteBid(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
