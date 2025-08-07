package com.enotes.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.api.dto.common.ApiResponse;
import com.enotes.api.dto.user.UserResponseDto;
import com.enotes.api.mapper.UserMapper;
import com.enotes.api.model.User;
import com.enotes.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController
{
	private final UserRepository userRepository;

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers()
	{
		List<User> users = userRepository.findAll();
		List<UserResponseDto> responseList = users.stream()
				.map(UserMapper::toResponse)
				.toList();

		return ResponseEntity
				.ok(ApiResponse.success(responseList, "All users fetched by admin"));
	}

	@GetMapping("/stats")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<String>> getAdminStats()
	{
		String stats = "Total users: " + userRepository.count();

		return ResponseEntity
				.ok(ApiResponse.success(stats, "Admin stats fetched successfully"));
	}
}
