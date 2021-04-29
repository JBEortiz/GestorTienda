package com.app.buys.eureka.service;

import java.util.List;

import com.app.buys.eureka.entity.Buys;

public interface BuysService {


	public Buys generateBuys();

	public List<Buys> findAll();

	public Buys findById(Long id);

	public void delete(Long id);

	public Buys update(Buys buys,Long id);
	
	public Buys findByIdUser(Long id);
	
	public Buys findByIdProducto(Long id);
}
