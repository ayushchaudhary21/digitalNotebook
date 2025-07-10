package com.note.Notes.Service;

import com.note.Notes.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserServiceInterface {
   void createUser(User user);
     User getuserInfo(long id);
     void deleteById(long id);
     User updateUser(long id,User user);
}
