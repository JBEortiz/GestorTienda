package com.app.gestion.service;

import java.util.List;

import com.app.gestion.entity.Inventory;

public interface InventoryService {

	public Inventory create(Inventory inventory);
	
	public Inventory findById(Long id);

	public List<Inventory> findAll();

	public Inventory addAmount(int amount, Long id);
	
	public Inventory addProduct(Long id,Long idProduct, Integer amount);
}
