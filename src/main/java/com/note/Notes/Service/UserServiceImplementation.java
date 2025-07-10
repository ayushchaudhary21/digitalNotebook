package com.note.Notes.Service;

import com.note.Notes.Entity.User;
import com.note.Notes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImplementation implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getuserInfo(long id) {
        Optional<User>user1=userRepository.findById(id);
        if(user1.isPresent())
        {
           User userDp=user1.get();
           return userDp;
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
       userRepository.deleteById(id);
    }

    @Override
    public User updateUser(long id, User user) {
       Optional<User>userDb=userRepository.findById(id);
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
       return null;
    }
}
