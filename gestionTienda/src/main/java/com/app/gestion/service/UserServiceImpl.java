package com.app.gestion.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.gestion.entity.Detail;
import com.app.gestion.entity.Inventory;
import com.app.gestion.entity.Product;
import com.app.gestion.entity.Users;
import com.app.gestion.repository.InventoryRepository;
import com.app.gestion.repository.UserRepository;
import com.app.gestion.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private InventoryRepository inventoryRepository;

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
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id no encontrado"));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Users update(Users user, Long id) {
		Users userUpdate = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id no encontrado"));
		if (userUpdate != null) {
			userUpdate.setName(user.getName());
			userUpdate.setDirection(Utils.validateEmail(user.getDirection()) );
			
			
		}
		return repository.save(userUpdate);

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
	@Transactional
	public Users buyProduct(Long idUser, Long idProduct, int amount) {
		Users user = getUserById(idUser);
		Inventory inventory = getInventoryById(idProduct);
		Double calculateTotal = Utils.calculateTotalPurchase(amount, inventory.getProduct().getPrice());
		String details = generatePurchaseDetails(inventory, amount, totalCalculation);
		boolean userHasTheProduct=hasProduct(user, inventory.getProduct());
		if (!userHasTheProduct) {
			handleProductPurchase(user, inventory, amount, totalCalculation, details);
		}
		if (userHasTheProduct){
			handleExistingProductPurchase(user, inventory, amount, totalCalculation);
		}

		return user;
	}

	private Users getUserById(Long idUser) {
		return repository.findById(idUser)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
	}

	private Inventory getInventoryById(Long idProduct) {
		return inventoryRepository.findById(idProduct)
				.orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
	}

	private String generatePurchaseDetails(Inventory inventory, int amount, Double totalCalculation) {
		String dateInfo = (inventory.getAmount() < amount) ? "Out of stock date" : " Date of purchase";
		return "Money moved: " + totalCalculation + " Amount: " + amount + " Product: "
				+ inventory.getProduct().getName() + dateInfo + " " + LocalDate.now();
	}

	private boolean hasProduct(Users user, Product product) {
		return user.getProducts() != null && user.getProducts().stream()
				.anyMatch(productUser -> productUser.getId().equals(product.getId()));
	}

	private void handleProductPurchase(Users user, Inventory inventory, int amount, Double totalCalculation, String details) {
		int amountActual = inventory.getAmount();
		int userAmountActual = user.getAmountProduct();

		if (amount < 0) {
			amount = 0;
		}

		if (inventory.getAmount() < amount) {
			List<Detail> detailsList = new ArrayList<>();
			detailsList = inventory.getDetails();
			Detail detail1 = new Detail();
			amount = inventory.getAmount();
			int totalByus = user.getAmountProduct() + amount;
			detail1.setDescription(details);
			detailsList.add(detail1);
			inventory.setDetails(detailsList);
			user.setAmountProduct(totalByus);
			inventory.setAmount(0);
			inventory.setTotal(inventory.getTotal() - calculateTotal);
		} else {
			List<Detail> detailsList = new ArrayList<>();
			detailsList = inventory.getDetails();
			Detail detail = new Detail();
			detail.setDescription(details);
			detailsList.add(detail);
			inventory.setDetails(detailsList);
			inventory.setAmount(amountActual - amount);
			inventory.setTotal(inventory.getTotal() - calculateTotal);
		}

		List<Product> listProduct = new ArrayList<>(user.getProducts());
		listProduct.add(inventory.getProduct());
		user.setProducts(listProduct);
	}


	@Override
	@Transactional
	public Users revertProduct(Long idUser, Long idProduct, int amount) {

		Double calculateTotal = 0.0;
		Users user = repository.findById(idProduct).orElseThrow(() -> new EntityNotFoundException("id no encontrado"));
		Inventory inventory = inventoryRepository.findById(idProduct).orElseThrow(() -> new EntityNotFoundException("id no encontrado"));
		int amountTotal = 0;
		int cantidadNoDevuelta = 0;
		List<Product> listProduct = new ArrayList<>();
		if (user.getProducts() != null) {
			listProduct = user.getProducts();
		}
		for (Product products : listProduct) {
			if (products.getId() == idProduct) {
				if (user.getAmountProduct() < amount) {
					amount = user.getAmountProduct();
					user.setAmountProduct(0);
					amountTotal = inventory.getAmount() + amount;
					calculateTotal = amount * inventory.getProduct().getPrice();
					inventory.setTotal(inventory.getTotal() + calculateTotal);
					inventory.setAmount(amountTotal);
					inventoryRepository.save(inventory);
				} else if (amount > 0 || amount == user.getAmountProduct()) {
					cantidadNoDevuelta = user.getAmountProduct() - amount;
					user.setAmountProduct(cantidadNoDevuelta);
					amountTotal = inventory.getAmount() + amount;
					user.setAmountProduct(cantidadNoDevuelta);
					calculateTotal = amount * inventory.getProduct().getPrice();
					inventory.setTotal(inventory.getTotal() + calculateTotal);
					inventory.setAmount(amountTotal);
					inventoryRepository.save(inventory);
				}

			}
		}

		return user;
	}

}
