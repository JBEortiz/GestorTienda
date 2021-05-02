package com.app.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.gestion.entity.Product;
public interface ProductRepository  extends JpaRepository<Product, Long>{

}
