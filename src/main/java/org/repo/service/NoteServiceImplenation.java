package org.repo.service;

import org.repo.dto.NotesRequestDTO;
import org.repo.dto.NotesResponseDTO;
import org.repo.entity.Notes;
import org.repo.entity.User;
import org.repo.exceptions.DuplicateTitle;
import org.repo.exceptions.NotesNotFoundException;
import org.repo.exceptions.UserNotFoundException;
import org.repo.repository.NoteRepository;
import org.repo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImplenation implements  NoteServiceInterface {

    private final NoteRepository noteRepository;
    private  final UserRepository userRepository;

    public NoteServiceImplenation(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    @Override
    public String saveNotes(String userName, NotesRequestDTO notesRequestDTO) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User userDB = userOptional.get();
            boolean alreadyExistNotes = userDB.getNotesList().stream().anyMatch(noteDb -> noteDb
                    .getNotesId() == (notesRequestDTO.getId()));

            if (alreadyExistNotes) {
                throw new DuplicateTitle("that notes are already exist ");
            }
            Notes notes=new Notes();
            notes.setNotesId(notesRequestDTO.getId());
            notes.setTitle(notesRequestDTO.getTitle());
            notes.setTitle(notesRequestDTO.getContent());
            notes.setUserId(userDB);
            noteRepository.save(notes);
            return "Notes are saved";
        } else {
            throw new UserNotFoundException("User does not found with userName :" + userName);
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
    public List<NotesResponseDTO> getAll(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getNotesList()
                    .stream()
                    .map(notes -> NotesResponseDTO.builder()
                            .title(notes.getTitle())
                            .content(notes.getContent())
                            .build())
                    .toList(); // ✅ Convert stream to List
        }
        throw new UserNotFoundException("There is no user with the userName "+userName);

        // there we have use custom exceptions
    }

    @Override
    public void deleteByTitle(String title, String userName) {
        Optional<User> userDb = userRepository.findByUserName(userName);
        if (userDb.isPresent()) {
            User user = userDb.get();
            Notes notes = user.getNotesList().stream().filter(titleDb -> titleDb
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
    public NotesResponseDTO updateByTitle(String userName, String title, NotesRequestDTO updatedNote) {
        Optional<User> userDb = userRepository.findByUserName(userName);

        if (userDb.isPresent()) {
            User user = userDb.get();

            // Find the note by title in the user's note list
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
            return NotesResponseDTO.builder()
                    .title(noteToUpdate.getTitle()).
                    content(noteToUpdate.getContent())

                    .build();

        } else {
            throw new UserNotFoundException("No user found with username: " + userName);
        }
    }
}

