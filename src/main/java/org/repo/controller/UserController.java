package org.repo.controller;

import org.repo.dto.UserRequestDTO;
import org.repo.dto.UserResponseDTO;
import org.repo.service.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceInterface userServiceInterface;

    public UserController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }


    @DeleteMapping("/delete")
     public ResponseEntity<String> delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return new ResponseEntity<>(userServiceInterface.deleteByUserName(userName), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
          return new ResponseEntity<>(userServiceInterface.updateUser(userName,userRequestDTO),HttpStatus.OK);
    }
}
