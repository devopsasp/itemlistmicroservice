package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<com.model.Item,Integer> {
    public Optional<com.model.Item> findByItemName(String  itemName);
}
