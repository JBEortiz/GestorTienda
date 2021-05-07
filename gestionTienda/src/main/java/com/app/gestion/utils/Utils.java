package com.app.gestion.utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Utils {
	
	public static Double  calculateTotalPurchase(int amount, Double price) {
		if(amount<0) {
			amount=0;
		}
		return ((price != null)? amount * price: 0);
	}
	public static String  purchaseDetails(int amount, String name) {
		if(amount<0) {
			amount=0;
		}
		if(name==null) {
			name="";
		}
		return  "Hemos recargado : " + amount + " Producto: " + name + "Fecha de recarga: "
				+ LocalDate.now();
	}
	
	public static String validateEmail(String direction) {
		
		String regexp="^[^@]+@[^@]+\\.[a-zA-Z]{2,}$";
		
		if(Pattern.matches(regexp, direction)) {
			return direction;
		}else {
			return "direccion por modificar no validada";
		}
		
	}
}
