package com.app.product.eureka.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.product.eureka.entity.Product;
import com.app.product.eureka.repository.ProductRepository;

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
		return repository.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public Product update(Product product, Long id) {
		Product productUpdate = repository.findById(id).get();
		if (productUpdate != null) {
			productUpdate.setName(product.getName());
			productUpdate.setPrice(product.getPrice());
			productUpdate.setAmount(product.getAmount());
		}
		return repository.save(productUpdate);

	}

	@Override
	public Product addAmount(int amount, Long id) {
		Product productAmount = repository.findById(id).get();
		Double calculateTotal= amount*productAmount.getPrice();
		Double totalInventary= productAmount.getTotal();
		int amountActual= productAmount.getAmount();
		if (productAmount != null) {
			productAmount.setAmount(amount);
			productAmount.setTotal(calculateTotal);
			if(productAmount.getAmount()==0||productAmount.getAmount()<0) {
				/*
				 * Dentro del inventario:
				 * Tiempo que ha tardado en venderse
				 * Producto vendidos:
				 * cantidad
				 */
				productAmount.setAmount(0);
				productAmount.setTotal(0.0);
				productAmount.setInventory("Dinero movido: "+totalInventary+" Producto: "+productAmount.getName() +"Fecha de agotamiento: "+LocalDate.now());
			}else if(productAmount.getAmount()>amountActual) {
				int totalRepuesto= productAmount.getAmount()-amountActual;
				productAmount.setInventory("Hemos recargado "+totalRepuesto+" del Producto: "+productAmount.getName() +"Fecha de recarga: "+LocalDate.now());
			}
		}
		return repository.save(productAmount);
	}

}
