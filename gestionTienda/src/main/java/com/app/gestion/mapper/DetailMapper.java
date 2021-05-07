package com.app.gestion.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.app.gestion.dto.DetailDTO;
import com.app.gestion.entity.Detail;

@Mapper
public interface DetailMapper extends GenericMapper<Detail,DetailDTO> {

	@Override
	@Mapping(source = "detail.description",target = "description")
	DetailDTO entityToDto(Detail detail);

	
	@Override
	@InheritInverseConfiguration
	Detail dtoToEntity(DetailDTO detailDto);
}
