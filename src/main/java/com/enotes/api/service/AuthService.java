package com.enotes.api.service;

import com.enotes.api.dto.auth.AuthResponse;
import com.enotes.api.dto.auth.LoginRequest;
import com.enotes.api.dto.auth.UserRegisterRequest;

public interface AuthService
{
	AuthResponse registerUser(UserRegisterRequest request);

	AuthResponse loginUser(LoginRequest request);
}
