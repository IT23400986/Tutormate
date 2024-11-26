package com.group13.tutormate.service;

import com.group13.tutormate.dto.UserDTO;
import com.group13.tutormate.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDto);

    User findByEmail(String email);

    List<UserDTO> findAllUsers();
}
