package com.app.product.eureka.service;

import java.util.List;

import com.app.product.eureka.entity.Product;

public interface ProductService {

	public Product create(Product product);

	public List<Product> findAll();

	public Product findById(Long id);

	public void delete(Long id);

	public Product update(Product product,Long id);
	
	public Product addAmount(int amount,Long id);
	
	
}
