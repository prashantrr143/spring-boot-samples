package com.prashant.spring.global.handlers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.prashant.spring.common.model.ApiResponse;

/**
 * Rest Exception handler at global level
 * 
 * @author prasingh26
 *
 */
@RestControllerAdvice
public class GlobalExceptionalHandler {
	
	
	
	@ExceptionHandler(TokenExpiredException.class)
	public ApiResponse handleException(TokenExpiredException e) {
		ApiResponse response = new ApiResponse();
		response.setStatus(200);
		response.setStatusDescription("Token Expired Due to inactivity");
		response.setError(e.getMessage());
		return response;
		
	}
	
	
	

}
