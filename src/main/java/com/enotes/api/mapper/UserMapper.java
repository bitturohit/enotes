package com.enotes.api.mapper;

import com.enotes.api.dto.user.UserResponseDto;
import com.enotes.api.model.User;

public class UserMapper
{
	public static UserResponseDto toResponse(User user)
	{
		return UserResponseDto.builder()
				.id(user.getId())
				.fullName(user.getFullName())
				.email(user.getEmail())
				.role(user.getRole())
				.enabled(user.isEnabled())
				.build();
	}
}
