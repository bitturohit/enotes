package com.enotes.api.mapper;

import com.enotes.api.dto.note.NoteRequestDto;
import com.enotes.api.dto.note.NoteResponseDto;
import com.enotes.api.model.Note;

public class NoteMapper
{
	public static Note toEntity(NoteRequestDto requestDto)
	{
		return Note.builder()
				.title(requestDto.getTitle())
				.content(requestDto.getContent())
				.build();
	}

	public static NoteResponseDto toResponse(Note note)
	{
		return NoteResponseDto.builder()
				.id(note.getId())
				.title(note.getTitle())
				.content(note.getContent())
				.createdAt(note.getCreatedAt())
				.build();
	}
}
