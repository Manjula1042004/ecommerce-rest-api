package com.manjula.ecommerce.service;

import com.manjula.ecommerce.dto.ItemRequestDTO;
import com.manjula.ecommerce.dto.ItemResponseDTO;
import com.manjula.ecommerce.exception.ResourceNotFoundException;
import com.manjula.ecommerce.model.Item;
import com.manjula.ecommerce.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    // Constructor injection
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Create new item
    @Transactional
    public ItemResponseDTO createItem(ItemRequestDTO itemRequestDTO) {
        log.info("Creating new item: {}", itemRequestDTO.getName());

        // Manual mapping from DTO to Entity
        Item item = new Item();
        item.setName(itemRequestDTO.getName());
        item.setDescription(itemRequestDTO.getDescription());
        item.setPrice(itemRequestDTO.getPrice());
        item.setQuantity(itemRequestDTO.getQuantity());
        item.setCategory(itemRequestDTO.getCategory());
        item.setBrand(itemRequestDTO.getBrand());
        item.setRating(itemRequestDTO.getRating() != null ? itemRequestDTO.getRating() : 0.0);
        item.setImageUrl(itemRequestDTO.getImageUrl());
        item.setIsActive(true);
        item.setCreatedAt(LocalDateTime.now());

        Item savedItem = itemRepository.save(item);

        log.info("Item created successfully with ID: {}", savedItem.getId());
        return convertToDTO(savedItem);
    }

    // Get item by ID
    public ItemResponseDTO getItemById(UUID id) {
        log.info("Fetching item with ID: {}", id);

        Item item = itemRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));

        return convertToDTO(item);
    }

    // Get all active items
    public List<ItemResponseDTO> getAllItems() {
        log.info("Fetching all active items");
        return itemRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update item
    @Transactional
    public ItemResponseDTO updateItem(UUID id, ItemRequestDTO itemRequestDTO) {
        log.info("Updating item with ID: {}", id);

        Item existingItem = itemRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));

        // Update fields manually
        existingItem.setName(itemRequestDTO.getName());
        existingItem.setDescription(itemRequestDTO.getDescription());
        existingItem.setPrice(itemRequestDTO.getPrice());
        existingItem.setQuantity(itemRequestDTO.getQuantity());
        existingItem.setCategory(itemRequestDTO.getCategory());
        existingItem.setBrand(itemRequestDTO.getBrand());
        if (itemRequestDTO.getRating() != null) {
            existingItem.setRating(itemRequestDTO.getRating());
        }
        existingItem.setImageUrl(itemRequestDTO.getImageUrl());
        existingItem.setUpdatedAt(LocalDateTime.now());

        Item updatedItem = itemRepository.save(existingItem);

        log.info("Item updated successfully: {}", id);
        return convertToDTO(updatedItem);
    }

    // Soft delete item
    @Transactional
    public void deleteItem(UUID id) {
        log.info("Soft deleting item with ID: {}", id);

        Item item = itemRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));

        item.setIsActive(false);
        item.setUpdatedAt(LocalDateTime.now());
        itemRepository.save(item);

        log.info("Item deleted successfully: {}", id);
    }

    // Search items by name
    public List<ItemResponseDTO> searchItemsByName(String name) {
        log.info("Searching items with name containing: {}", name);
        return itemRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get items by category
    public List<ItemResponseDTO> getItemsByCategory(String category) {
        log.info("Fetching items by category: {}", category);
        return itemRepository.findByCategoryAndIsActiveTrue(category)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert Entity to DTO manually
    private ItemResponseDTO convertToDTO(Item item) {
        ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setCategory(item.getCategory());
        dto.setBrand(item.getBrand());
        dto.setRating(item.getRating());
        dto.setImageUrl(item.getImageUrl());
        dto.setCreatedAt(item.getCreatedAt());
        dto.setUpdatedAt(item.getUpdatedAt());
        dto.setIsActive(item.getIsActive());
        return dto;
    }
}