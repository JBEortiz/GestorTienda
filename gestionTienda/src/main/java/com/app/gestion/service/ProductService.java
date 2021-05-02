package com.app.gestion.service;

import java.util.List;

import com.app.gestion.entity.Product;

public interface ProductService {

	public Product create(Product product);

	public List<Product> findAll();

	public Product findById(Long id);

	public void delete(Long id);

	public Product update(Product product,Long id);
	
}
