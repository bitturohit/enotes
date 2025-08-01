package com.enotes.api.dto.note;

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
public class NoteResponseDto
{
	private long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
}
