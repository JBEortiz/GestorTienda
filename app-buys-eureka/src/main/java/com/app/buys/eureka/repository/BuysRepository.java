package com.app.buys.eureka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.buys.eureka.entity.Buys;

public interface BuysRepository extends JpaRepository<Buys, Long>{

}
