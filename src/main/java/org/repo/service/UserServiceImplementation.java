package org.repo.service;

import org.repo.dto.UserRequestDTO;
import org.repo.dto.UserResponseDTO;
import org.repo.entity.User;
import org.repo.exceptions.DuplicateEmail;
import org.repo.exceptions.DuplicateUser;
import org.repo.exceptions.UserNotFoundException;
import org.repo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImplementation implements UserServiceInterface{


    private  final UserRepository userRepository;
    private final  PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createUser(UserRequestDTO userRequestDTO) {
       if(userRepository.findByUserName(userRequestDTO.getUserName()).isPresent()){
           throw new DuplicateUser("Username already is taken "+userRequestDTO.getUserName());
       }

       if(userRepository.findByUserEmail(userRequestDTO.getUserEmail()).isPresent())
       {
           throw new DuplicateEmail("Email is already taken :"+ userRequestDTO.getUserEmail());
       }
       User user=new User();
       user.setUserName(userRequestDTO.getUserName());
       user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
       user.setUserEmail(userRequestDTO.getUserEmail());
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
    public String deleteByUserName(String userName) {
        Optional<User> userOptional =userRepository.findByUserName(userName);
        if(userOptional.isPresent())
        {
            userRepository.deleteById(userOptional.get().getId());
            return "User is Deleted";
        }
        throw new UserNotFoundException("User does Found with having id "+userName);
    }



    @Override
    public UserResponseDTO updateUser(String userName, UserRequestDTO userRequestDTO) {
       Optional<User> userOptional =userRepository.findByUserName(userName);
       if(userOptional.isPresent())
       {
           User updatedUser= userOptional.get();

           if(userRequestDTO.getUserName()!=null && userRepository.findByUserName(userRequestDTO.getUserName()).isEmpty()) {
               updatedUser.setUserName(userRequestDTO.getUserName());
           } else throw new DuplicateUser("userName is already taken choose different user "+userName);
           if(userRequestDTO.getUserEmail()!=null && userRepository.findByUserEmail(userRequestDTO.getUserEmail()).isEmpty())
           {
               updatedUser.setUserEmail(userRequestDTO.getUserEmail());
           }else throw  new DuplicateEmail("email is already in use");
            if(userRequestDTO.getPassword()!=null)
           {
               updatedUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
           }
           userRepository.save(updatedUser);
           return UserResponseDTO.builder()
                   .id(updatedUser.getId())
                   .userName(updatedUser.getUserName())
                   .userEmail(updatedUser.getUserEmail())
                   .listOfNotes(List.of())
                   .build();

       }
     throw new UserNotFoundException("User does not found with UserName :" +userName);
    }
}

