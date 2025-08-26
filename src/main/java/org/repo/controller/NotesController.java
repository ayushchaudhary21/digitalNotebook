package org.repo.controller;

import org.repo.dto.NotesRequestDTO;
import org.repo.dto.NotesResponseDTO;
import org.repo.service.NoteServiceInterface;
import org.repo.service.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/Notes")
public class NotesController {

    private final NoteServiceInterface noteServiceInterface;

    private final UserServiceInterface userServiceInterface;

    public NotesController(NoteServiceInterface noteServiceInterface, UserServiceInterface userServiceInterface) {
        this.noteServiceInterface = noteServiceInterface;
        this.userServiceInterface = userServiceInterface;
    }


    @PostMapping()
    public ResponseEntity<String> createNotes(@RequestBody NotesRequestDTO notesRequestDTO)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        return new ResponseEntity<>(noteServiceInterface.saveNotes(userName,notesRequestDTO),HttpStatus.CREATED);

    }

    @GetMapping("/getbytitle/{title}")
    public ResponseEntity<?> getByUserName( @PathVariable("title") String title)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
         return new ResponseEntity<>(noteServiceInterface.getbyName(userName,title),HttpStatus.OK);
    }



      @GetMapping("/getAll")
        public ResponseEntity<List<NotesResponseDTO>> getALl()
        {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            return new ResponseEntity<>(noteServiceInterface.getAll(userName),HttpStatus.OK);
        }




    @DeleteMapping("/deletebytitle/{title}")
    public ResponseEntity<?> deteleByTitle(@PathVariable ("title") String title)
        {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            noteServiceInterface.deleteByTitle(title,userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }




        @PutMapping("/updatebytitle/{title}")
     public ResponseEntity<NotesResponseDTO> updateByTitle(@PathVariable ("title") String title,
                                            @RequestBody NotesRequestDTO notesRequestDTO)
        {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            return new ResponseEntity<>(noteServiceInterface.updateByTitle(userName,title,notesRequestDTO),HttpStatus.OK);
        }
}
