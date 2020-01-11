package com.ugurcangursen.UserLoginRegister.service;

import com.ugurcangursen.UserLoginRegister.dto.UserDTO;

public interface UserService {

    UserDTO save(UserDTO user);

    UserDTO getById(Long id);

    UserDTO getByUsername(String username);
}
