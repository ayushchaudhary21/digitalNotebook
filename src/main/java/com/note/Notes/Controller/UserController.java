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
        return new ResponseEntity<>(userServiceInterface.createUser(user),HttpStatus.CREATED);

    }
    @GetMapping("/getbyuserName/{userName}")
    public ResponseEntity<User>getUserInfo(@PathVariable("userName") String userName)
    {
        return new ResponseEntity<>(userServiceInterface.getuserInfo(userName),HttpStatus.OK);
    }

     @DeleteMapping("/deletebyid/{id}")
     public ResponseEntity<?> delete(@PathVariable("id") long userId)
     {
         userServiceInterface.deleteById(userId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable ("userName") String userName,
                                        @RequestBody User user)
    {
          return new ResponseEntity<>(userServiceInterface.updateUser(userName,user),HttpStatus.OK);
    }
}
