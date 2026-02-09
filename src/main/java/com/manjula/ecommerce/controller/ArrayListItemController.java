package com.manjula.ecommerce.controller;

import com.manjula.ecommerce.dto.ApiResponse;
import com.manjula.ecommerce.dto.ItemRequestDTO;
import com.manjula.ecommerce.dto.ItemResponseDTO;
import com.manjula.ecommerce.service.ArrayListItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/arraylist/items")
public class ArrayListItemController {

    private final ArrayListItemService arrayListItemService;

    public ArrayListItemController(ArrayListItemService arrayListItemService) {
        this.arrayListItemService = arrayListItemService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemResponseDTO>> createItem(
            @Valid @RequestBody ItemRequestDTO itemRequestDTO) {

        ItemResponseDTO createdItem = arrayListItemService.createItem(itemRequestDTO);
        ApiResponse<ItemResponseDTO> response = ApiResponse.success(
                "Item created successfully using ArrayList storage", createdItem);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponseDTO>> getItemById(@PathVariable UUID id) {

        ItemResponseDTO item = arrayListItemService.getItemById(id);
        ApiResponse<ItemResponseDTO> response = ApiResponse.success(
                "Item retrieved successfully from ArrayList storage", item);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemResponseDTO>>> getAllItems() {
        List<ItemResponseDTO> items = arrayListItemService.getAllItems();
        ApiResponse<List<ItemResponseDTO>> response = ApiResponse.success(
                "Items retrieved successfully from ArrayList storage", items);

        return ResponseEntity.ok(response);
    }
}