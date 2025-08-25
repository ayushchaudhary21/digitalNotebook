package org.repo.service;

import org.repo.dto.NotesRequestDTO;
import org.repo.dto.NotesResponseDTO;
import org.repo.entity.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteServiceInterface {
    String saveNotes(String userName, NotesRequestDTO notesRequestDTO);
    Notes getbyName(String userName, String title);
    List<NotesResponseDTO>getAll(String userName);
    void deleteByTitle(String title,String userName);
    NotesResponseDTO updateByTitle(String userName,String title,NotesRequestDTO notesRequestDTO);
}
