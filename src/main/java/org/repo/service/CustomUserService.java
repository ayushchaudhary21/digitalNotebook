package org.repo.service;

import org.repo.configuration.CustomUserDetail;
import org.repo.entity.User;
import org.repo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>userOptional=userRepository.findByUserName(username);
         if(!userOptional.isPresent())
         {
             throw new UsernameNotFoundException("User is not present with UserName :" +username);
         }
         return new CustomUserDetail(userOptional.get());
    }
}
// this is used to encode the both the Authorization provider that is encoder password and UserDetailService the
// bottom layer of security.