package com.enotes.api.dto.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T>
{
	private int status;
	private String message;
	private LocalDateTime timestamp;
	private T data;

	public static <T> ApiResponse<T> success(T data, String message)
	{
		return ApiResponse.<T>builder()
				.status(200)
				.message(message)
				.timestamp(LocalDateTime.now())
				.data(data)
				.build();
	}
}
