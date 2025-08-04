package com.enotes.api.exception;

public class ResourceNotFoundException extends RuntimeException
{
	public ResourceNotFoundException(String resourceName, String fieldName,
			Object fieldvalue)
	{
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName,
				fieldvalue));
	}

	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
