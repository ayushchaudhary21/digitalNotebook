package com.note.Notes.Controller;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Service.NoteServiceInterface;
import com.note.Notes.Service.UserServiceInterface;
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
    @Autowired
    private UserServiceInterface userServiceInterface;



    @PostMapping("/{userName}")
    public ResponseEntity<?> createNotes(@PathVariable("userName") String userName,
                                @RequestBody Notes notes)
    {

        return new ResponseEntity<>(noteServiceInterface.saveNotes(userName,notes),HttpStatus.CREATED);

    }



    @GetMapping("/getbyid/{id}/{userName}")
    public ResponseEntity<?> getById(@PathVariable String userName,@PathVariable ("id") long id)
    {
        return new ResponseEntity<>(noteServiceInterface.getNotesById(userName,id),HttpStatus.OK);
    }


    @GetMapping("/getbytitle/{title}/{userName}")
    public ResponseEntity<?> getByUserName(@PathVariable ("userName") String userName
                                           , @PathVariable("title") String title)
    {
         return new ResponseEntity<>(noteServiceInterface.getbyName(userName,title),HttpStatus.OK);
    }



      @GetMapping("getAll/{userName}")
        public ResponseEntity<?> getALl(@PathVariable("userName") String userName)
        {
            return new ResponseEntity<>(noteServiceInterface.getAll(userName),HttpStatus.OK);
        }




    @DeleteMapping("/deletebytitle/{title}/{userName}")
    public ResponseEntity<?> deteleByTitle(@PathVariable ("title") String title
                                              ,@PathVariable("userName") String userName)
        {
            noteServiceInterface.deleteByTitle(title,userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }




        @PutMapping("/updatebytitle/{userName}/{title}")
     public ResponseEntity<?> updateByTitle(@PathVariable("userName") String userName,@PathVariable ("title") String title,
                                            @RequestBody Notes note)
        {
            return new ResponseEntity<>(noteServiceInterface.updateByTitle(userName,title,note),HttpStatus.OK);
        }
}
