package com.enotes.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enotes.api.dto.NoteRequestDto;
import com.enotes.api.dto.NoteResponseDto;
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
		return noteRepository.findAll().stream().map(NoteMapper::toResponse).toList();
	}

}
