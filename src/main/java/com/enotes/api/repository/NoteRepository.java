package com.enotes.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enotes.api.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>
{
	List<Note> findAllByIsArchivedFalse();

	Page<Note> findAllByIsArchivedFalse(Pageable pageable);

	List<Note> findAllByIsArchivedTrue();
}
