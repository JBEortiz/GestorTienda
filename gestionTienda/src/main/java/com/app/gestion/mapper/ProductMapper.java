package com.app.gestion.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.app.gestion.dto.ProductDTO;
import com.app.gestion.entity.Product;

@Mapper
public interface ProductMapper extends GenericMapper<Product, ProductDTO> {

	@Override
	@Mapping(source = "product.price",target = "price")
	@Mapping(source = "product.name",target = "name")
	@Mapping(source = "product.createAt",target = "createAt")
	ProductDTO entityToDto(Product product);

	
	@Override
	@InheritInverseConfiguration
	Product dtoToEntity(ProductDTO productDto);
}
