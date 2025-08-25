package org.repo.controller;

import org.repo.dto.UserRequestDTO;
import org.repo.service.UserServiceInterface;
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
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userServiceInterface.createUser(userRequestDTO), HttpStatus.CREATED);

    }

}
