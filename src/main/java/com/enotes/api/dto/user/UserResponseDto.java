package com.enotes.api.dto.user;

import com.enotes.api.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto
{
	private Long id;
	private String fullName;
	private String email;
	private Role role;
	private boolean enabled;
}
