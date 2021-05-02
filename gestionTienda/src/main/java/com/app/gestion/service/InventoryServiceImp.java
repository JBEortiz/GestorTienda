package com.app.gestion.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.gestion.entity.Detail;
import com.app.gestion.entity.Inventory;
import com.app.gestion.entity.Product;
import com.app.gestion.repository.InventoryRepository;
import com.app.gestion.repository.ProductRepository;
import com.app.gestion.utils.Utils;

@Service
public class InventoryServiceImp implements InventoryService {

	@Autowired
	private InventoryRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional
	public Inventory create(Inventory inventory) {
		return repository.save(inventory);
	}

	@Override
	@Transactional(readOnly = true)
	public Inventory findById(Long id) {
		return repository.findById(id).orElseGet(() -> new Inventory());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Inventory> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Inventory addAmount(int amount, Long id) {
		String details = "";
		Inventory inventory = repository.findById(id).orElseGet(() -> new Inventory());
		List<Detail> detailsList = new ArrayList<>();
		detailsList = inventory.getDetails();
		Product product = inventory.getProduct();
		Double calculateTotal = Utils.calculateTotalPurchase((inventory.getAmount() + amount), product.getPrice());

		int amountActual = inventory.getAmount();

		if (inventory != null) {
			inventory.setTotal(calculateTotal);
			if (inventory.getAmount() == 0 || inventory.getAmount() < 0) {
				Detail detail = new Detail();
				inventory.setAmount(amount);
				inventory.setTotal(calculateTotal);
				details = Utils.purchaseDetails(amount, product.getName());
				detail.setDescription(details);
				detailsList.add(detail);
				inventory.setDetails(detailsList);

			} else {
				Detail detail1 = new Detail();
				inventory.setAmount(amountActual + amount);
				inventory.setTotal(calculateTotal);
				details = Utils.purchaseDetails(amount, product.getName());
				detail1.setDescription(details);
				detailsList.add(detail1);
				inventory.setDetails(detailsList);
			}
		}
		return repository.save(inventory);
	}

	@Override
	@Transactional
	public Inventory addProduct(Long id, Long idProduct, Integer amount) {

		Product product = productRepository.findById(idProduct).orElseGet(() -> new Product());
		Inventory inventory = repository.findById(id).orElseGet(() -> new Inventory());
		Double calculateTotal = (inventory.getAmount() + amount) * product.getPrice();
		int amountTotalGuardar=inventory.getAmount()+amount;
		if (inventory.getProduct() == null) {
			inventory.setProduct(product);
			inventory.setAmount(amountTotalGuardar);
			inventory.setTotal(calculateTotal);

		}else {
			inventory.setAmount(amountTotalGuardar);
			inventory.setTotal(calculateTotal);
		}

		return inventory;
	}
	
	

}
