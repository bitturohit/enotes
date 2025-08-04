package com.enotes.api.service;

import java.util.List;

import com.enotes.api.dto.common.PageResponse;
import com.enotes.api.dto.note.NoteRequestDto;
import com.enotes.api.dto.note.NoteResponseDto;

public interface NoteService
{
	NoteResponseDto createNote(NoteRequestDto noteDto);

	List<NoteResponseDto> getAllNotes();

	NoteResponseDto getNoteById(Long noteId);

	PageResponse<NoteResponseDto> getNotesPages(int page, int size);

	NoteResponseDto archiveNote(Long id);

	List<NoteResponseDto> getAllArchivednotes();

	NoteResponseDto unArchiveNote(Long id);

	NoteResponseDto updateNote(Long id, NoteRequestDto requestDto);

	void deleteNote(Long id);

	List<NoteResponseDto> getDeletedNotes();

	NoteResponseDto restoreDeletedNote(Long id);

	void permanentlyDeleteNote(Long id);
}
