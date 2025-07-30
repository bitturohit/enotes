package com.enotes.api.dto.note;

import jakarta.validation.constraints.NotBlank;
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
public class NoteRequestDto
{
	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Message is required")
	private String content;
}
