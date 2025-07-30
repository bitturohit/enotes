package com.enotes.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.api.dto.auth.AuthResponse;
import com.enotes.api.dto.auth.LoginRequest;
import com.enotes.api.dto.auth.UserRegisterRequest;
import com.enotes.api.dto.common.ApiResponse;
import com.enotes.api.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController
{
	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthResponse>> registerUser(
			@Valid @RequestBody UserRegisterRequest request)
	{
		AuthResponse response = authService.registerUser(request);

		return ResponseEntity
				.ok(ApiResponse.success(response, "User registered successfully"));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> loginUser(
			@Valid @RequestBody LoginRequest request)
	{
		AuthResponse response = authService.loginUser(request);

		return ResponseEntity.ok(ApiResponse.success(response, "Login successfull"));
	}
}
