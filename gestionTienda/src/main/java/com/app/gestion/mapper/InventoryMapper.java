package com.app.gestion.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.app.gestion.dto.InventoryDTO;
import com.app.gestion.entity.Inventory;

@Mapper
public interface InventoryMapper extends GenericMapper<Inventory,InventoryDTO> {

	
	@Override
	@Mapping(source = "inventory.amount",target = "amount")
	@Mapping(source = "inventory.total",target = "total")
	@Mapping(source = "inventory.details",target = "details")
	@Mapping(source = "inventory.product",target = "product")
	InventoryDTO entityToDto(Inventory inventory);

	
	@Override
	@InheritInverseConfiguration
	Inventory dtoToEntity(InventoryDTO inventoryDto);
}
