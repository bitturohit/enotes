package com.enotes.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.api.dto.ApiResponse;
import com.enotes.api.dto.NoteRequestDto;
import com.enotes.api.dto.NoteResponseDto;
import com.enotes.api.dto.PageResponse;
import com.enotes.api.service.NoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController
{
	private final NoteService noteService;

	@PostMapping
	public ResponseEntity<ApiResponse<NoteResponseDto>> createNote(
			@Valid @RequestBody NoteRequestDto noteDto)
	{
		NoteResponseDto response = noteService.createNote(noteDto);
		return ResponseEntity
				.ok(ApiResponse.success(response, "Note created succesfully"));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<NoteResponseDto>>> findAllNotes()
	{
		List<NoteResponseDto> responseList = noteService.getAllNotes();
		return ResponseEntity.ok(ApiResponse.success(responseList, "Fetched all notes"));
	}

	@GetMapping("/paginated")
	public ResponseEntity<ApiResponse<PageResponse<NoteResponseDto>>> getNotesPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size)
	{
		PageResponse<NoteResponseDto> response = noteService.getNotesPages(page, size);

		return ResponseEntity.ok(ApiResponse.success(response, "Notes page fetched"));
	}

	@PutMapping("/{id}/archive")
	public ResponseEntity<ApiResponse<NoteResponseDto>> archiveNote(@PathVariable Long id)
	{
		NoteResponseDto response = noteService.archiveNote(id);

		return ResponseEntity.ok(ApiResponse.success(response, "Note archived"));
	}

	@PutMapping("/{id}/unarchive")
	public ResponseEntity<ApiResponse<NoteResponseDto>> unarchiveNote(
			@PathVariable Long id)
	{
		NoteResponseDto response = noteService.unArchiveNote(id);

		return ResponseEntity.ok(ApiResponse.success(response, "Note restored"));
	}

	@GetMapping("/archived")
	public ResponseEntity<ApiResponse<List<NoteResponseDto>>> getArchivedNotes()
	{
		List<NoteResponseDto> archivedNotes = noteService.getAllArchivednotes();
		return ResponseEntity
				.ok(ApiResponse.success(archivedNotes, "Archived notes fetched"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<NoteResponseDto>> updateNote(
			@PathVariable Long id,
			@RequestBody @Valid NoteRequestDto noteDto)
	{
		NoteResponseDto response = noteService.updateNote(id, noteDto);

		return ResponseEntity
				.ok(ApiResponse.success(response, "Note updated successfully"));
	}
}
