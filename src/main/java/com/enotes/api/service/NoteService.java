package com.enotes.api.service;

import java.util.List;

import com.enotes.api.dto.NoteRequestDto;
import com.enotes.api.dto.NoteResponseDto;
import com.enotes.api.dto.PageResponse;

public interface NoteService
{
	NoteResponseDto createNote(NoteRequestDto noteDto);

	List<NoteResponseDto> getAllNotes();

	PageResponse<NoteResponseDto> getNotesPages(int page, int size);
}
