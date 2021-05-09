package com.app.gestion.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.gestion.entity.Product;
import com.app.gestion.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	@Transactional
	public Product create(Product producto) {
		return repository.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id no encontrado"));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public Product update(Product product, Long id) {
		Product productUpdate = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id no encontrado"));
		if (productUpdate != null) {
			productUpdate.setName(product.getName());
			productUpdate.setPrice(product.getPrice());
		}
		return repository.save(productUpdate);

	}

	

}
