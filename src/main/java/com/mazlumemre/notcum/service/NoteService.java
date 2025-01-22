package com.mazlumemre.notcum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazlumemre.notcum.entity.Note;
import com.mazlumemre.notcum.repository.NoteRepository;


@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Not bulunamadı"));
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }


}
