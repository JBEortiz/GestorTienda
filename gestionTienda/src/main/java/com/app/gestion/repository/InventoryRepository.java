package com.app.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.gestion.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
