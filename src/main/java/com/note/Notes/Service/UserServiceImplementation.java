package com.note.Notes.Service;

import com.note.Notes.Entity.User;
import com.note.Notes.Exceptions.DuplicateEmail;
import com.note.Notes.Exceptions.DuplicateUser;
import com.note.Notes.Exceptions.NotesNotFoundException;
import com.note.Notes.Exceptions.UserNotFoundException;
import com.note.Notes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImplementation implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(User user) {
       Optional<User> userDb=userRepository.findByUserName(user.getUserName());
       if(userDb.isPresent()){
           throw new DuplicateUser("Username already is taken "+user.getUserName());
       }
       Optional<User>userDbMail=userRepository.findByUserEmail(user.getUserEmail());
       if(userDbMail.isPresent())
       {
           throw new DuplicateEmail("Email is already taken :"+user.getUserEmail());
       }
         userRepository.save(user);
       return "User are saved in the database";

    }

    @Override
    public User getuserInfo(String userName) {
        Optional<User>user1=userRepository.findByUserName(userName);
        if(user1.isPresent())
        {
           User userDp=user1.get();
           return userDp;
        }
        throw new UserNotFoundException("User does Found with having UserName :"+userName);
    }

    @Override
    public String deleteById(long id) {
        Optional<User>user1=userRepository.findById(id);
        if(user1.isPresent())
        {
            userRepository.deleteById(id);
            return "User is Deleted";
        }
        throw new UserNotFoundException("User does Found with having id "+id);
    }



    @Override
    public User updateUser(String userName, User user) {
       Optional<User>userDb=userRepository.findByUserName(userName);
       if(userDb.isPresent())
       {
           User updatedUser=userDb.get();
           if(user.getUserName()!=null) {
               updatedUser.setUserName(user.getUserName());
           }
           if(user.getUserEmail()!=null)
           {
               updatedUser.setUserEmail(user.getUserEmail());
           }
           if(user.getPassword()!=null)
           {
               updatedUser.setPassword(user.getPassword());
           }
           userRepository.save(updatedUser);
           return updatedUser;
       }
     throw new UserNotFoundException("User does not found with UserName :" +userName);
    }
}

