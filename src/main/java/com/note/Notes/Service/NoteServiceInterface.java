package com.note.Notes.Service;

import com.note.Notes.Entity.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteServiceInterface {
    String saveNotes(String userName,Notes notes);
    Notes getNotesById(String userName,long id);
    Notes getbyName(String userName,String title);
    List<Notes>getAll(String userName);
    void deleteByTitle(String title,String userName);
    Notes updateByTitle(String userName,String title,Notes note);
}
