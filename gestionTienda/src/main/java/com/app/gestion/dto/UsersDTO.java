package com.app.gestion.dto;

import java.util.List;

import com.app.gestion.entity.Rol;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsersDto {

	private String username;
	private String password;
	private String name;
	private String lastname;
	private String direction;
	private Integer amountProduct;
	private List<Rol> roles;
	private List<ProductDTO> products;
}
