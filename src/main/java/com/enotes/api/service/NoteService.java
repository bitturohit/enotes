package com.enotes.api.service;

import java.util.List;

import com.enotes.api.dto.NoteRequestDto;
import com.enotes.api.dto.NoteResponseDto;

public interface NoteService
{
	NoteResponseDto createNote(NoteRequestDto noteDto);

	List<NoteResponseDto> getAllNotes();
}
