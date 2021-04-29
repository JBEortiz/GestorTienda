package com.app.buys.eureka.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.buys.eureka.entity.Buys;
import com.app.buys.eureka.repository.BuysRepository;
import com.app.product.eureka.entity.Product;
import com.app.product.eureka.repository.ProductRepository;
import com.app.user.eureka.entity.Users;
import com.app.user.eureka.repository.UserRepository;

@Service
public class BuysServiceImpl implements BuysService {

	@Autowired
	private BuysRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Buys generateBuys() {
		Buys buysGenerate = new Buys();
		Users user = userRepository.findById(buysGenerate.getUser().getId()).get();
		Product product = productRepository.findById(buysGenerate.getProduct().getId()).get();
		buysGenerate.setFechaCompra(LocalDate.now());
		buysGenerate.setProduct(product);
		buysGenerate.setUser(user);
		
		return buysGenerate;
	}

	@Override
	public List<Buys> findAll() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public Buys findById(Long id) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public Buys update(Buys buys, Long id) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public Buys findByIdUser(Long id) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public Buys findByIdProducto(Long id) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}
