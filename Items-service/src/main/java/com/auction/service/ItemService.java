package com.auction.service;

import com.auction.dto.ItemRequest;
import com.auction.dto.ItemResponse;
import com.auction.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

     Optional<ItemResponse> addItem(ItemRequest item);
     Optional<ItemResponse> getItemById(Long id);
     Optional<ItemResponse> updateItem(Long id,ItemRequest item);
     List<ItemResponse> getItems();
     boolean deleteItem(Long id);

}
