package com.note.Notes.Controller;

import com.note.Notes.Entity.Notes;
import com.note.Notes.Entity.User;
import com.note.Notes.Service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;


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
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
          return new ResponseEntity<>(userServiceInterface.updateUser(userName,user),HttpStatus.OK);
    }
}
