package com.app.product.eureka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.product.eureka.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
