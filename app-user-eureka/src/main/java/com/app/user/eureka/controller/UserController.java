package com.app.user.eureka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.app.user.eureka.entity.Users;
import com.app.user.eureka.service.UserService;
 
@RestController
public class UserController {

	@Autowired
	private UserService service;
	/* not found
	@Autowired
	private Environment enviroment;
	
	 * not found
	 * 
	@Value("${configuracion.texto}")
	private String texto;
	
	@Value("${server.port}")
	private String port;
	*/
	
	@GetMapping("users")
	public List<Users> listar(){
		return service.findAll();
	}
	
	@GetMapping("users/{id}")
	public Users findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping("user/{username}")
	public Users findById(@PathVariable String username) {
		return service.findByUsername(username);
	}
	
	@PostMapping("users/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Users create(@RequestBody Users user) {
		return service.create(user);
	}
	
	@PutMapping("users/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Users update(@RequestBody Users user, @PathVariable Long id) {
		return service.update(user,id);
	}
	
	@PutMapping("users/comprar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Users buyProduct(Long idUser, Long idProduct, int amount) {
		return service.buyProduct(idUser, idProduct, amount);
	}
}
