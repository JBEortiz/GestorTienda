package com.app.gestion.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InventoryDTO {

	private Integer amount;
	private Double total;
	private List<DetailDTO> details;
	private ProductDTO product;
}
