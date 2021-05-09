package com.app.gestion.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.gestion.entity.Product;
import com.app.gestion.service.ProductService;

@RestController
public class ProductController {
	
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Product>> listar(){
		List<Product> products = service.findAll();
		if (products.isEmpty()) {
			throw new EntityNotFoundException("la lista de usuarios esta vacia");
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product;
		product = service.findById(id);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return ResponseEntity.ok(service.create(product));
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id) {
		return ResponseEntity.ok(service.update(product,id));
	}
	
	
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleted(@PathVariable Long id) {
		service.delete(id);
	}
	

}