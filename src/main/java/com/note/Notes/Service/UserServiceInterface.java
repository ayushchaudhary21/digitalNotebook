package com.note.Notes.Service;

import com.note.Notes.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserServiceInterface {
    String createUser(User user);

    User getuserInfo(String userName);

    String deleteById(long id);

    User updateUser(String userName, User user);
}