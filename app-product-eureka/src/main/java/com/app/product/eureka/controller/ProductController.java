package com.app.product.eureka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.product.eureka.entity.Product;
import com.app.product.eureka.service.ProductService;

@RestController
public class ProductController {
	
	
	@Autowired
	private ProductService service;
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
	
	@GetMapping("/listar")
	public List<Product> listar(){
		return service.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public Product findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return service.create(product);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		return service.update(product,id);
	}
	
	@PutMapping("/editar/{id}/cantidad/{cantidad}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product addAmount(@PathVariable Long id,@PathVariable int cantidad) {
		return service.addAmount(cantidad,id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleted(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	
	/*
	@GetMapping("/obtener-config")
	public ResponseEntity<?> getConfig(){
		Map<String,String> json= new HashMap<>();
		json.put("texto", texto);
		json.put("port", port);
		
		return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
	}*/
	

}