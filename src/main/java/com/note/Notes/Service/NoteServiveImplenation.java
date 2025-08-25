package com.note.Notes.Service;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Entity.User;
import com.note.Notes.Exceptions.DuplicateTitle;
import com.note.Notes.Exceptions.NotesNotFoundException;
import com.note.Notes.Exceptions.UserNotFoundException;
import com.note.Notes.Repository.NoteRepository;
import com.note.Notes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiveImplenation implements  NoteServiceInterface {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public String saveNotes(String userName, Notes notes) {
        Optional<User> userDb = userRepository.findByUserName(userName);
        if (userDb.isPresent()) {
            User user = userDb.get();
            boolean alreadyExistNotes = user.getNotesList().stream().anyMatch(noteDb -> noteDb
                    .getNotesId() == (notes.getNotesId()));

            if (alreadyExistNotes) {
                throw new DuplicateTitle("that notes are already exist ");
            }
            notes.setUserId(user);
            noteRepository.save(notes);

            return "Notes are saved";
        } else {
            throw new UserNotFoundException("User does not found with userName :" + userName);
        }
    }

    @Override
    public Notes getNotesById(String userName, long id) {
        Optional<User> userDb = userRepository.findByUserName(userName);
        if (userDb.isPresent()) {
            User user = userDb.get();
            return
                    user.getNotesList()
                            .stream()
                            .filter(notes -> notes.getNotesId() == id)
                            .findFirst()
                            .orElseThrow(() -> new NotesNotFoundException("No Notes are present with id : " + id));
        } else {
            throw new UserNotFoundException("No user with UserName : " + userName);
        }
    }

    @Override
    public Notes getbyName(String userName, String title) {
        Optional<User> userDb = userRepository.findByUserName(userName);
        if (userDb.isPresent()) {
            User user = userDb.get();
            return
                    user.getNotesList()
                            .stream()
                            .filter(notes -> notes.getTitle().equalsIgnoreCase(title))
                            .findFirst()
                            .orElseThrow(() -> new NotesNotFoundException("No Notes are present with title: " + title));
        } else {
            throw new UserNotFoundException("No user with UserName : " + userName);
        }
    }

    @Override
    public List<Notes> getAll(String userName) {
        Optional<User> userDb = userRepository.findByUserName(userName);
        if (userDb.isPresent()) {
            User user = userDb.get();
            return user.getNotesList();
        }
        throw new UserNotFoundException("No user with UserName :" + userName); // there we have use custom exceptions
    }

    @Override
    public void deleteByTitle(String title, String userName) {
        Optional<User> userDb = userRepository.findByUserName(userName);
        if (userDb.isPresent()) {
            User user = userDb.get();
            Notes notes = user.getNotesList().stream().filter(tilteDb -> tilteDb
                            .getTitle()
                            .equalsIgnoreCase(title)).findFirst()
                    .orElseThrow(() -> new NotesNotFoundException("Notes are found with title : " + title));
            user.getNotesList().remove(notes);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("No user with UserName :" + userName);
        }
    }


    @Override
    public Notes updateByTitle(String userName, String title, Notes updatedNote) {
        Optional<User> userDb = userRepository.findByUserName(userName);

        if (userDb.isPresent()) {
            User user = userDb.get();

            // Find the note by title in the user's notes list
            Notes noteToUpdate = user.getNotesList()
                    .stream()
                    .filter(note -> note.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElseThrow(() ->
                            new NotesNotFoundException("No note found with title: " + title));

            // Update content if present
            if (updatedNote.getContent() != null) {
                noteToUpdate.setContent(updatedNote.getContent());
            }

            // Update title if present
            if (updatedNote.getTitle() != null) {
                noteToUpdate.setTitle(updatedNote.getTitle());
            }

            noteRepository.save(noteToUpdate);
            return noteToUpdate;

        } else {
            throw new UserNotFoundException("No user found with username: " + userName);
        }
    }
}