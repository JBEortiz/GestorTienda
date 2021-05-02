package com.app.gestion.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.gestion.entity.Users;
import com.app.gestion.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	/*
	 * not found
	 * 
	 * @Autowired private Environment enviroment;
	 * 
	 * not found
	 * 
	 * @Value("${configuracion.texto}") private String texto;
	 * 
	 * @Value("${server.port}") private String port;
	 */

	@GetMapping("/users")
	public ResponseEntity<List<Users>> listar() {
		List<Users> users = service.findAll();
		if (users.isEmpty()) {
			throw new EntityNotFoundException("la lista de usuarios esta vacia");
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Users> findById(@PathVariable Long id) {

		Users user = null;
		if (service.findById(id) == null) {
			new EntityNotFoundException("id no encontrado");
		}
		user = service.findById(id);
		return ResponseEntity.ok(user);

	}

	@GetMapping("/user/{username}")
	public ResponseEntity<Users> findById(@PathVariable String username) {
		Users user = null;
		if (service.findByUsername(username) == null) {
			new EntityNotFoundException("username no encontrado");
		}
		user = service.findByUsername(username);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/users/crear")
	public ResponseEntity<Users> create(@RequestBody Users user) {
		return ResponseEntity.ok(service.create(user));
	}

	@PutMapping("/users/editar/{id}")
	public ResponseEntity<Users> update(@RequestBody Users user, @PathVariable Long id) {
		return ResponseEntity.ok(service.update(user, id));
	}

	@PutMapping("users/{idUser}/comprar/{idProduct}/{amount}")
	public ResponseEntity<Users> buyProduct(@PathVariable Long idUser,@PathVariable Long idProduct,@PathVariable int amount) {
		return ResponseEntity.ok(service.buyProduct(idUser, idProduct, amount));
	}
	@PutMapping("users/{idUser}/devolver/{idProduct}/{amount}")
	public ResponseEntity<Users> revertProduct(@PathVariable Long idUser,@PathVariable Long idProduct,@PathVariable int amount) {
		return ResponseEntity.ok(service.revertProduct(idUser, idProduct, amount));
	}
	
	
}
