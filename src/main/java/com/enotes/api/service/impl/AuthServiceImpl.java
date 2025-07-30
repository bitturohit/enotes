package com.enotes.api.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enotes.api.dto.auth.AuthResponse;
import com.enotes.api.dto.auth.LoginRequest;
import com.enotes.api.dto.auth.UserRegisterRequest;
import com.enotes.api.exception.ResourceNotFoundException;
import com.enotes.api.model.User;
import com.enotes.api.repository.UserRepository;
import com.enotes.api.service.AuthService;
import com.enotes.api.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Override
	public AuthResponse registerUser(UserRegisterRequest request)
	{
		if (userRepository.existsByEmail(request.getEmail()))
		{
			throw new IllegalStateException("Email already in use");
		}

		User user = User.builder()
				.fullName(request.getFullName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role("USER")
				.build();

		userRepository.save(user);

		String token = jwtUtil.generateToken(user.getEmail());

		return AuthResponse.builder()
				.token(token)
				.message("User registered successfully")
				.build();
	}

	@Override
	public AuthResponse loginUser(LoginRequest request)
	{
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(
						() -> new ResourceNotFoundException("Invalid email or password"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
		{
			throw new IllegalStateException("Invalid email or password");
		}

		String token = jwtUtil.generateToken(request.getEmail());

		return AuthResponse.builder().token(token).message("Login successful").build();
	}

}
