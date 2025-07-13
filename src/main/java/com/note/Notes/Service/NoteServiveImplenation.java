package com.note.Notes.Service;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiveImplenation implements  NoteServiceInterface {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void saveNotes(Notes notes) {
        noteRepository.save(notes);
    }

    @Override
    public Notes getNotesById(long id) {
        Optional<Notes> noteDb = noteRepository.findById(id);
        if (noteDb.isPresent()) {
            Notes notes = noteDb.get();
            return notes;
        }
        return null;
    }

    @Override
    public Notes getbyName(String title) {
        Optional<Notes> noteDb = noteRepository.findByTitle(title);
        if (noteDb.isPresent()) {
            Notes notes = noteDb.get();
            return notes;
        }
        return null;
    }

    @Override
    public List<Notes> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteByTitle(String title) {
        noteRepository.deleteByTitle(title);

    }

    @Override
    public Notes updateByTitle(String title, Notes note) {
        Optional<Notes> notesDb = noteRepository.findByTitle(title);
        if (notesDb.isPresent()) {
            Notes notes = notesDb.get();
            if (note.getContent() != null) {
                notes.setContent(note.getContent());
            }
            if (note.getTitle() != null) {
                notes.setTitle(note.getTitle());
            }
            noteRepository.save(notes);
            return notes;
        }
        return null;
    }
}