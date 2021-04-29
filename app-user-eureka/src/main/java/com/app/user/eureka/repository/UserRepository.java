package com.app.user.eureka.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.user.eureka.entity.Users;
public interface UserRepository extends JpaRepository<Users,Long>{
	
	public Users findByUsername(String username);

}
