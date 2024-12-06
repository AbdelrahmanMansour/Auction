package com.auction.repository;

import com.auction.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ItemsRepository extends JpaRepository<Item, Long> {
}
