package com.enotes.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.enotes.api.dto.common.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler
{
	// Handles @Valid validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(
			MethodArgumentNotValidException ex)
	{
		List<String> validationErrors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> fieldError.getDefaultMessage())
				.toList();

		ErrorResponse response = ErrorResponse.builder()
				.message("Validation failed")
				.status(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.errors(validationErrors)
				.build();

		return ResponseEntity.badRequest().body(response);
	}

	// Handle illegal business logic (like editing archived notes)
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException ex)
	{
		ErrorResponse response = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.errors(List.of("Illegal operation"))
				.build();

		return ResponseEntity.badRequest().body(response);
	}

	// Handle custom 404 errors
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(
			ResourceNotFoundException ex)
	{
		ErrorResponse response = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now())
				.errors(List.of("Resource not found"))
				.build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	// Catch-all handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleOtherexceptions(Exception ex)
	{
		ErrorResponse response = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(LocalDateTime.now())
				.errors(List.of("Unexpected error occured"))
				.build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
