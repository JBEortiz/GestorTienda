package com.app.gestion.dto;

import java.util.List;

import com.app.gestion.entity.Rol;

public class UsersDTO {

	private String username;
	private String password;
	private Boolean enabled;
	private String name;
	private String lastname;
	private String direction;
	private Integer amountProduct;
	private List<Rol> roles;
	private List<ProductDTO> products;
}
