package com.note.Notes.Controller;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Entity.User;
import com.note.Notes.Service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userServiceInterface.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/getbyId/{id}")
    public ResponseEntity<User>getUserInfo(@PathVariable("id") long userId)
    {
        return new ResponseEntity<>(userServiceInterface.getuserInfo(userId),HttpStatus.OK);
    }

     @DeleteMapping("/deletebyid/{id}")
     public ResponseEntity<?> delete(@PathVariable("id") long userId)
     {
         userServiceInterface.deleteById(userId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
    @PutMapping("/update/{userid}")
    public ResponseEntity<?> updateUser(@PathVariable ("userid") long userId,
                                        @RequestBody User user)
    {
          return new ResponseEntity<>(userServiceInterface.updateUser(userId,user),HttpStatus.OK);
    }
}
