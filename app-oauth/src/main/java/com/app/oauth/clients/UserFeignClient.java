package com.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.user.eureka.entity.Users;

@FeignClient(name="app-user-eureka")
public interface UserFeignClient {
	
	@GetMapping("users/search/user/{username}")
	public Users findByUsername(@PathVariable String username);


}
