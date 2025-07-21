package com.enotes.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.api.dto.NoteRequestDto;
import com.enotes.api.dto.NoteResponseDto;
import com.enotes.api.service.NoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController
{
	private final NoteService noteService;

	// POST /api/notes with JSON body:
	// {
	// "title": "Spring Boot",
	// "content": "Build enotes project step by step"
	// }
	@PostMapping
	public ResponseEntity<NoteResponseDto> createNote(
			@Valid @RequestBody NoteRequestDto noteDto)
	{
		return ResponseEntity.ok(noteService.createNote(noteDto));
	}

	// GET /api/notes
	@GetMapping
	public ResponseEntity<List<NoteResponseDto>> findAllNotes()
	{
		return ResponseEntity.ok(noteService.getAllNotes());
	}
}
