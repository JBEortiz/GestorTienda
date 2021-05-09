package com.app.gestion.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.gestion.dto.InventoryDTO;
import com.app.gestion.entity.Inventory;
import com.app.gestion.mapper.InventoryMapper;
import com.app.gestion.service.InventoryService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
public class InventoryController {

	private final InventoryService service;
	
	private  InventoryMapper mapper;
	
	@GetMapping("/inventory/{id}")
	public ResponseEntity<Inventory> findById(@PathVariable Long id) {
		Inventory inventory;
		//InventoryDTO inventoryDTO= new InventoryDTO();
		inventory = service.findById(id);
		//inventoryDTO=mapper.entityToDto(inventory);
		return ResponseEntity.ok(inventory);
	}
	
	@GetMapping("/inventoryAll")
	public ResponseEntity<List<Inventory>> listar(){
		List<Inventory> products = service.findAll();
		if (products.isEmpty()) {
			throw new EntityNotFoundException("la lista de usuarios esta vacia");
		}
		return ResponseEntity.ok(products);
	}
	
	@PostMapping("/inventory/crear")
	public ResponseEntity<Inventory> create(@RequestBody Inventory inventory) {
		return ResponseEntity.ok(service.create(inventory));
	}
	
	@PutMapping("/editar/{id}/cantidad/{amount}")
	public ResponseEntity<Inventory> addAmount(@PathVariable Long id,@PathVariable Integer amount) {
		return ResponseEntity.ok(service.addAmount(amount,id));
	}
	@PutMapping("/inventary/{id}/{idProduct}/cantidad/{amount}")
	public ResponseEntity<Inventory> addProduct(@PathVariable Long id,@PathVariable Long idProduct,@PathVariable Integer amount) {
		return ResponseEntity.ok(service.addProduct(id, idProduct, amount));
	}
	
	
}
