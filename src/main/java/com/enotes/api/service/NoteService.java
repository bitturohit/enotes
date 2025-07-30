package com.enotes.api.service;

import java.util.List;

import com.enotes.api.dto.common.PageResponse;
import com.enotes.api.dto.note.NoteRequestDto;
import com.enotes.api.dto.note.NoteResponseDto;

public interface NoteService
{
	NoteResponseDto createNote(NoteRequestDto noteDto);

	List<NoteResponseDto> getAllNotes();

	PageResponse<NoteResponseDto> getNotesPages(int page, int size);

	NoteResponseDto archiveNote(Long id);

	NoteResponseDto unArchiveNote(Long id);

	List<NoteResponseDto> getAllArchivednotes();

	NoteResponseDto updateNote(Long id, NoteRequestDto requestDto);

	void deleteNote(Long id);
}
