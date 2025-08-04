package com.enotes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enotes.api.model.Note;
import com.enotes.api.model.User;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>
{
	List<Note> findAllByIsArchivedFalse();

	Page<Note> findAllByIsArchivedFalse(Pageable pageable);

	List<Note> findAllByIsArchivedTrue();

	List<Note> findByUser(User user);

	List<Note> findByUserAndIsArchivedFalse(User user);

	Page<Note> findByUserAndIsArchivedFalse(User user, Pageable pageable);

	List<Note> findByUserAndIsArchivedTrue(User user);

	List<Note> findByUserAndDeletedFalse(User user);

	Optional<Note> findByIdAndUser(long noteId, User user);

	Optional<Note> findByIdAndUserAndDeletedFalse(long id, User user);
}
