package com.app.gestion.dto;

import java.util.List;

import com.app.gestion.entity.Product;

public class InventoryDTO {

	private Integer amount;

	private Double total;

	private List<DetailDTO> details;
	
	private ProductDTO product;
}
