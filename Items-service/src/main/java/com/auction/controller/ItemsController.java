package com.auction.controller;


import com.auction.dto.ItemRequest;
import com.auction.dto.ItemResponse;
import com.auction.entity.Item;
import com.auction.service.ItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemsController {

    ItemService itemService;

    ModelMapper modelMapper;

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody ItemRequest itemRequest) {
        itemService.addItem(itemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item created successfully");
    }

    @GetMapping("/getItem")
    public ResponseEntity<Optional<ItemResponse>> getItem(@RequestParam ("id") Long id) {
        Optional<ItemResponse> response = itemService.getItemById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @PutMapping("/updateItem")
    public ResponseEntity<Optional<ItemResponse>> updateItem(@RequestParam ("id") Long id, @RequestBody ItemRequest itemRequest) {
        Optional<ItemResponse> response = itemService.updateItem(id, itemRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<Optional<Void>> deleteItem(@RequestParam ("id") Long id) {
        if(itemService.deleteItem(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
