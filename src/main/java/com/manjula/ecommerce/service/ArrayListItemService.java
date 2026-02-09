package com.manjula.ecommerce.service;

import com.manjula.ecommerce.dto.ItemRequestDTO;
import com.manjula.ecommerce.dto.ItemResponseDTO;
import com.manjula.ecommerce.exception.ResourceNotFoundException;
import com.manjula.ecommerce.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ArrayListItemService {

    // Using ConcurrentHashMap for thread-safe in-memory storage
    private final ConcurrentHashMap<UUID, Item> items = new ConcurrentHashMap<>();

    // Create item using ArrayList storage
    public ItemResponseDTO createItem(ItemRequestDTO request) {
        UUID id = UUID.randomUUID();
        Item item = new Item();
        item.setId(id);
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        item.setCategory(request.getCategory());
        item.setBrand(request.getBrand());
        item.setRating(request.getRating() != null ? request.getRating() : 0.0);
        item.setImageUrl(request.getImageUrl());
        item.setIsActive(true);

        items.put(id, item);

        return convertToDTO(item);
    }

    // Get item by ID from ArrayList storage
    public ItemResponseDTO getItemById(UUID id) {
        Item item = items.get(id);
        if (item == null || !item.getIsActive()) {
            throw new ResourceNotFoundException("Item not found with ID: " + id);
        }
        return convertToDTO(item);
    }

    // Get all items from ArrayList
    public List<ItemResponseDTO> getAllItems() {
        List<ItemResponseDTO> result = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getIsActive()) {
                result.add(convertToDTO(item));
            }
        }
        return result;
    }

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