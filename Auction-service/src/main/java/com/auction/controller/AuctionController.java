package com.auction.controller;

import com.auction.dto.AuctionRequest;
import com.auction.dto.AuctionResponse;
import com.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auction")
@AllArgsConstructor
public class AuctionController {

    AuctionService auctionService;

    @PostMapping("/addAuction")
    public ResponseEntity<String> addAuction(@RequestBody AuctionRequest AuctionRequest) {
        auctionService.addAuction(AuctionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Auction created successfully");

    }

    @GetMapping("/getAuction")
    public ResponseEntity<Optional<AuctionResponse>> getAuction(@RequestParam ("id") Long id) {
        Optional<AuctionResponse> response = auctionService.getAuction(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllAuctions")
    public ResponseEntity<List<AuctionResponse>> getAllAuctions() {
        return ResponseEntity.ok(auctionService.getAllAuctions());
    }

    @PutMapping("/updateItem")
    public ResponseEntity<Optional<AuctionResponse>> updateAuction(@RequestParam ("id") Long id, @RequestBody AuctionRequest AuctionRequest) {
        Optional<AuctionResponse> response = auctionService.updateAuction(id, AuctionRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<Optional<AuctionResponse>> deleteAuction(@RequestParam ("id") Long id) {
        if(auctionService.deleteAuction(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
