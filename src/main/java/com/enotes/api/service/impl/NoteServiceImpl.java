package com.enotes.api.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enotes.api.dto.NoteRequestDto;
import com.enotes.api.dto.NoteResponseDto;
import com.enotes.api.dto.PageResponse;
import com.enotes.api.mapper.NoteMapper;
import com.enotes.api.model.Note;
import com.enotes.api.repository.NoteRepository;
import com.enotes.api.service.NoteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService
{
	private final NoteRepository noteRepository;

	@Override
	public NoteResponseDto createNote(NoteRequestDto noteDto)
	{
		Note saved = noteRepository.save(NoteMapper.toEntity(noteDto));
		return NoteMapper.toResponse(saved);
	}

	@Override
	public List<NoteResponseDto> getAllNotes()
	{
		return noteRepository.findAllByIsArchivedFalse()
				.stream()
				.map(NoteMapper::toResponse)
				.toList();
	}

	@Override
	public PageResponse<NoteResponseDto> getNotesPages(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);

		Page<Note> notePage = noteRepository.findAllByIsArchivedFalse(pageable);
		Page<NoteResponseDto> dtoPage = notePage.map(NoteMapper::toResponse);

		return PageResponse.of(dtoPage);
	}

	@Override
	public NoteResponseDto archiveNote(Long id)
	{
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Note not found"));
		note.setArchived(true);
		Note updated = noteRepository.save(note);

		return NoteMapper.toResponse(updated);
	}

	@Override
	public NoteResponseDto unArchiveNote(Long id)
	{
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Note not found"));
		note.setArchived(false);
		Note updated = noteRepository.save(note);

		return NoteMapper.toResponse(updated);
	}

	@Override
	public List<NoteResponseDto> getAllArchivednotes()
	{
		List<Note> notes = noteRepository.findAllByIsArchivedTrue();

		return notes.stream().map(NoteMapper::toResponse).toList();
	}

}
