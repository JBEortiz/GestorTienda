package com.app.gestion.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	private String name;
	
	private Double price;
	
	private LocalDate createAt;

}
