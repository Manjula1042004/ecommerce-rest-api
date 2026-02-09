package com.manjula.ecommerce.repository;

import com.manjula.ecommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    // Find active items
    List<Item> findByIsActiveTrue();

    // Find by ID and active status
    Optional<Item> findByIdAndIsActiveTrue(UUID id);

    // Search by name (contains, case-insensitive)
    List<Item> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);

    // Find by category
    List<Item> findByCategoryAndIsActiveTrue(String category);

    // Find items with price less than
    List<Item> findByPriceLessThanAndIsActiveTrue(Double price);

    // Find items with price between
    List<Item> findByPriceBetweenAndIsActiveTrue(Double minPrice, Double maxPrice);
}