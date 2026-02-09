package com.manjula.ecommerce.controller;

import com.manjula.ecommerce.dto.ApiResponse;
import com.manjula.ecommerce.dto.ItemRequestDTO;
import com.manjula.ecommerce.dto.ItemResponseDTO;
import com.manjula.ecommerce.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    // Constructor injection
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemResponseDTO>> createItem(
            @Valid @RequestBody ItemRequestDTO itemRequestDTO) {

        ItemResponseDTO createdItem = itemService.createItem(itemRequestDTO);
        ApiResponse<ItemResponseDTO> response = ApiResponse.success(
                "Item created successfully", createdItem);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponseDTO>> getItemById(@PathVariable UUID id) {

        ItemResponseDTO item = itemService.getItemById(id);
        ApiResponse<ItemResponseDTO> response = ApiResponse.success(
                "Item retrieved successfully", item);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemResponseDTO>>> getAllItems() {
        List<ItemResponseDTO> items = itemService.getAllItems();
        ApiResponse<List<ItemResponseDTO>> response = ApiResponse.success(
                "Items retrieved successfully", items);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponseDTO>> updateItem(
            @PathVariable UUID id,
            @Valid @RequestBody ItemRequestDTO itemRequestDTO) {

        ItemResponseDTO updatedItem = itemService.updateItem(id, itemRequestDTO);
        ApiResponse<ItemResponseDTO> response = ApiResponse.success(
                "Item updated successfully", updatedItem);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@PathVariable UUID id) {

        itemService.deleteItem(id);
        ApiResponse<Void> response = ApiResponse.success("Item deleted successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ItemResponseDTO>>> searchItemsByName(
            @RequestParam String name) {

        List<ItemResponseDTO> items = itemService.searchItemsByName(name);
        ApiResponse<List<ItemResponseDTO>> response = ApiResponse.success(
                "Search completed successfully", items);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ItemResponseDTO>>> getItemsByCategory(
            @PathVariable String category) {

        List<ItemResponseDTO> items = itemService.getItemsByCategory(category);
        ApiResponse<List<ItemResponseDTO>> response = ApiResponse.success(
                "Items retrieved successfully", items);

        return ResponseEntity.ok(response);
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        return ResponseEntity.ok(ApiResponse.success("ECommerce API is running"));
    }
}