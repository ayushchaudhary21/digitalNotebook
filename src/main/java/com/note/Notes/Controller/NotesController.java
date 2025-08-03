package com.note.Notes.Controller;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Service.NoteServiceInterface;
import com.note.Notes.Service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/Notes")
public class NotesController {
    @Autowired
    private NoteServiceInterface noteServiceInterface;
    @Autowired
    private UserServiceInterface userServiceInterface;



    @PostMapping()
    public ResponseEntity<?> createNotes(@RequestBody Notes notes)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        return new ResponseEntity<>(noteServiceInterface.saveNotes(userName,notes),HttpStatus.CREATED);

    }



    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") long id)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        return new ResponseEntity<>(noteServiceInterface.getNotesById(userName,id),HttpStatus.OK);
    }


    @GetMapping("/getbytitle/{title}")
    public ResponseEntity<?> getByUserName( @PathVariable("title") String title)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
         return new ResponseEntity<>(noteServiceInterface.getbyName(userName,title),HttpStatus.OK);
    }



      @GetMapping("getAll")
        public ResponseEntity<?> getALl()
        {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            return new ResponseEntity<>(noteServiceInterface.getAll(userName),HttpStatus.OK);
        }




    @DeleteMapping("/deletebytitle/{title}")
    public ResponseEntity<?> deteleByTitle(@PathVariable ("title") String title
                                              )
        {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            noteServiceInterface.deleteByTitle(title,userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }




        @PutMapping("/updatebytitle/{title}")
     public ResponseEntity<?> updateByTitle(@PathVariable ("title") String title,
                                            @RequestBody Notes note)
        {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            return new ResponseEntity<>(noteServiceInterface.updateByTitle(userName,title,note),HttpStatus.OK);
        }
}
