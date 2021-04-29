package com.app.buys.eureka.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.product.eureka.entity.Product;
import com.app.user.eureka.entity.Users;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "buys")
public class Buys implements Serializable{
	
	private static final long serialVersionUID = 4166693811111743066L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate fechaCompra;

	@ManyToOne(fetch = FetchType.EAGER)
	private Users user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;

	

	
	
	
	
}
