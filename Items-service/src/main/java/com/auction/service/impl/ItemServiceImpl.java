package com.auction.service.impl;

import com.auction.config.ModelMapperConfig;
import com.auction.dto.ItemRequest;
import com.auction.dto.ItemResponse;
import com.auction.entity.Item;
import com.auction.exception.CustomException;
import com.auction.exception.ItemNotFoundException;
import com.auction.repository.ItemsRepository;
import com.auction.service.ItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ItemResponse> addItem(ItemRequest itemRequest) {
        try {
            Item response = itemsRepository.save(modelMapper.map(itemRequest, Item.class));
            return Optional.of(modelMapper.map(response, ItemResponse.class));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Failed to save item due to constraint violation", e);
        }
    }

    @Override
    public Optional<ItemResponse> getItemById(Long id) {
        Item item = itemsRepository.findById(id)
                .orElseThrow(ItemNotFoundException.throwItemNotFoundException(id));
        return Optional.of(modelMapper.map(item, ItemResponse.class));
    }

    @Override
    public Optional<ItemResponse> updateItem(Long id, ItemRequest itemrequest) {
        Item updatedItem = itemsRepository.findById(id)
                .map(item -> {
                    item.setItemName(itemrequest.getItemName());
                    item.setItemDescription(item.getItemDescription());
                    item.setCategory(itemrequest.getCategory());
                    return itemsRepository.save(item);
                }).orElseThrow(ItemNotFoundException.throwItemNotFoundException(id));
        return Optional.of(modelMapper.map(updatedItem, ItemResponse.class));
    }

    @Override
    public List<ItemResponse> getItems() {
        return itemsRepository.findAll()
                .stream()
                .map(item-> modelMapper.map(item, ItemResponse.class))
                .toList();
    }

    @Override
    public boolean deleteItem(Long id) {
        itemsRepository.findById(id)
                .map(item ->{
                    itemsRepository.deleteById(id);
                    return true;
                });
        return false;
    }
}
