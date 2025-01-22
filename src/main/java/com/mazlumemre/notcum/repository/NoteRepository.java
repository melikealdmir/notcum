package com.mazlumemre.notcum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mazlumemre.notcum.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	
	
}

