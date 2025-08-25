package org.repo.service;

import org.repo.dto.UserRequestDTO;
import org.repo.dto.UserResponseDTO;
import org.repo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {
    String createUser(UserRequestDTO userRequestDTO);

    User getuserInfo(String userName);

    String deleteByUserName(String userName);

    UserResponseDTO updateUser(String userName, UserRequestDTO userRequestDTO);
}