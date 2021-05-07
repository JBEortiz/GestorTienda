package com.app.gestion.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.app.gestion.dto.UsersDto;
import com.app.gestion.entity.Users;
@Mapper
public interface UserMapper extends GenericMapper<Users, UsersDto> {

	@Override
	@Mapping(source = "user.username",target = "username")
	@Mapping(source = "user.password",target = "password")
	@Mapping(source = "user.name",target = "name")
	@Mapping(source = "user.lastname",target = "lastname")
	@Mapping(source = "user.direction",target = "direction")
	@Mapping(source = "user.amountProduct",target = "amountProduct")
	@Mapping(source = "user.roles",target = "roles")
	@Mapping(source = "user.products",target = "products")
	UsersDto entityToDto(Users user);

	
	@Override
	@InheritInverseConfiguration
	Users dtoToEntity(UsersDto userDto);
}
