package com.note.Notes.Service;

import com.note.Notes.Entity.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteServiceInterface {
    void saveNotes(Notes notes);
    Notes getNotesById(long id);
    Notes getbyName(String title);
    List<Notes>getAll();
    void deleteByTitle(String title);
    Notes updateByTitle(String title,Notes note);
}
