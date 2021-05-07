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
		return repository.findById(id).orElseGet(() -> new Users());
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Users update(Users user, Long id) {
		Users userUpdate = repository.findById(id).orElseGet(() -> new Users());
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
		String details = "";
		Double calculateTotal = 0.0;
		Users user = repository.findById(idUser).orElseGet(() -> null);
		Inventory inventory = inventoryRepository.findById(idProduct).orElseGet(() -> null);
		Boolean tieneProducto = false;
		Product product = new Product();
		if (inventory != null) {
			List<Product> listProduct = new ArrayList<>();
			if (user.getProducts() != null) {
				listProduct = user.getProducts();
			}
			for (Product productUser : listProduct) {
				if (productUser.getId() != inventory.getProduct().getId()) {
					product = inventory.getProduct();
					tieneProducto = false;
				} else {
					tieneProducto = true;
				}
			}
			if (!tieneProducto) {

				List<Detail> detailsList = new ArrayList<>();
				detailsList = inventory.getDetails();
				int amountActual = inventory.getAmount();
				int userAmountActual = user.getAmountProduct();
				if (amount < 0) {
					amount = 0;
				}
				if (inventory.getAmount() < amount) {
					Detail detail1 = new Detail();
					amount = inventory.getAmount();
					calculateTotal = Utils.calculateTotalPurchase(amount, inventory.getProduct().getPrice());
					details = "Dinero movido: " + calculateTotal + " Cantidad: " + amount + " Producto: "
							+ inventory.getProduct().getName() + "Fecha de agotamiento: " + LocalDate.now();
					int totalByus = user.getAmountProduct() + amount;
					detail1.setDescription(details);
					detailsList.add(detail1);
					inventory.setDetails(detailsList);
					user.setAmountProduct(totalByus);
					inventory.setAmount(0);
					product = inventory.getProduct();
					inventory.setTotal(inventory.getTotal() - calculateTotal);
				} else {
					Detail detail = new Detail();

					calculateTotal = Utils.calculateTotalPurchase(amount, inventory.getProduct().getPrice());
					details = "Dinero movido: " + calculateTotal + " Cantidad: " + amount + " Producto: "
							+ inventory.getProduct().getName() + "Fecha de compra: " + LocalDate.now();
					user.setAmountProduct(userAmountActual + amount);
					detail.setDescription(details);
					detailsList.add(detail);
					inventory.setDetails(detailsList);
					inventory.setAmount(amountActual - amount);
					inventory.setTotal(inventory.getTotal() - calculateTotal);
					product = inventory.getProduct();
				}

				listProduct.add(product);
				user.setProducts(listProduct);
			} else {
				calculateTotal = Utils.calculateTotalPurchase(amount, inventory.getProduct().getPrice());
				user.setAmountProduct(user.getAmountProduct() + amount);
				inventory.setAmount(inventory.getAmount() - amount);
				inventory.setTotal(inventory.getTotal() - calculateTotal);
			}
		}

		return user;
	}

	@Override
	@Transactional
	public Users revertProduct(Long idUser, Long idProduct, int amount) {

		Double calculateTotal = 0.0;
		Users user = repository.findById(idProduct).orElseGet(() -> new Users());
		Inventory inventory = inventoryRepository.findById(idProduct).orElseGet(() -> new Inventory());
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
