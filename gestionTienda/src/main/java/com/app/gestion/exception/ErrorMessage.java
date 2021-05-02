package com.app.gestion.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorMessage {

	private final String message;
	private final Integer statusCode;
	
	

}
