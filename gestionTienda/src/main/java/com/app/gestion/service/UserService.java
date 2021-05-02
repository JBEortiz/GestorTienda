package com.app.gestion.service;

import java.util.List;

import com.app.gestion.entity.Users;

public interface UserService {
	
	public Users create(Users user);

	public List<Users> findAll();

	public Users findById(Long id);

	public void delete(Long id);

	public Users update(Users user,Long id);
	
	public Users findByUsername(String username);
	
	public Users buyProduct(Long idUser, Long idProduct,int amount);
	
	public Users revertProduct(Long idUser, Long idProduct,int amount);

}
