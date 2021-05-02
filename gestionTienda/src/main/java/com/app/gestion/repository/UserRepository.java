package com.app.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.gestion.entity.Users;
public interface UserRepository extends JpaRepository<Users, Long>{

	public Users findByUsername(String username);
}
