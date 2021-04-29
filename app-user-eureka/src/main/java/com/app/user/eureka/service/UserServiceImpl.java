package com.app.user.eureka.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.product.eureka.entity.Product;
import com.app.product.eureka.repository.ProductRepository;
import com.app.user.eureka.entity.Users;
import com.app.user.eureka.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional
	public Users create(Users producto) {
		return repository.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		return (List<Users>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Users findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Users update(Users product, Long id) {
		Users productUpdate = repository.findById(id).get();
		if (productUpdate != null) {
			productUpdate.setName(product.getName());
		}
		return repository.save(productUpdate);

	}

	@Override
	@Transactional
	public Users findByUsername(String username) {
		Users user = repository.findByUsername(username);
		if (user == null) {
			return new Users();
		}
		return user;
	}

	@Override
	public Users buyProduct(Long idUser, Long idProduct, int amount) {
		Double calculateTotal = 0.0;
		Users user = repository.findById(idProduct).orElseGet(null);
		Product product = productRepository.findById(idProduct).orElseGet(null);
		int amountActual= product.getAmount();
		if (amount < 0) {
			amount = 0;
		}
		if (product.getAmount() < amount) {
			amount = product.getAmount();
			product.setAmount(0);
			calculateTotal = amount * product.getPrice();
			product.setInventory("Dinero movido: " + calculateTotal + " Cantidad: " + amount + " Producto: "
					+ product.getName() + "Fecha de agotamiento: " + LocalDate.now());
		} else {
			product.setAmount(amountActual- amount);
			product.setInventory("Dinero movido: " + calculateTotal + " Cantidad: " + amount + " Producto: "
					+ product.getName() + "Fecha de agotamiento: " + LocalDate.now());
		}
		List<Product> listProduct = user.getProducts();
		listProduct.add(product);
		user.setProducts(listProduct);
		
		return user;
	}

}
