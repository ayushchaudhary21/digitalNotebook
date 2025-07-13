package com.note.Notes.Controller;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Service.NoteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("Notes")
public class NotesController {
    @Autowired
    private NoteServiceInterface noteServiceInterface;

    @PostMapping
    public ResponseEntity<?> createNotes(@RequestBody Notes notes)
    {
        noteServiceInterface.saveNotes(notes);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") long id)
    {
        return new ResponseEntity<>(noteServiceInterface.getNotesById(id),HttpStatus.OK);
    }
    @GetMapping("/getbytitle/{title}")
    public ResponseEntity<?> getByUserName(@PathVariable ("title") String userName)
    {
         return new ResponseEntity<>(noteServiceInterface.getbyName(userName),HttpStatus.OK);
    }
      @GetMapping("getAll")
        public ResponseEntity<?> getALl()
        {
            return new ResponseEntity<>(noteServiceInterface.getAll(),HttpStatus.OK);
        }

        @DeleteMapping("/deletebytitle/{title}")
    public ResponseEntity<?> deteleByTitle(@PathVariable ("title") String title)
        {
            noteServiceInterface.deleteByTitle(title);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        @PutMapping("/updatebytitle/{title}")
     public ResponseEntity<?> updateByTitle(@PathVariable ("title") String title,
                                            @RequestBody Notes note)
        {
            return new ResponseEntity<>(noteServiceInterface.updateByTitle(title,note),HttpStatus.OK);
        }
}
