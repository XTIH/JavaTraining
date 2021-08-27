package com.bookstorewebapp.BookstoreWebApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Custom exception that is created to handle problems when we cannot find a resource in the 
 * database. The REST API will throw this exception and return the HttpStatus.NOT_FOUND back to the
 * client */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
