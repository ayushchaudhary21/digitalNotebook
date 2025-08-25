package com.note.Notes.Controller;

import com.note.Notes.Entity.User;
import com.note.Notes.Service.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// for the register
@RestController
@RequestMapping("/register")
public class PublicController {
    private final UserServiceInterface userServiceInterface;

    public PublicController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceInterface.createUser(user), HttpStatus.CREATED);

    }

}
