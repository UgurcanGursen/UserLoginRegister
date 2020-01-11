package com.ugurcangursen.UserLoginRegister.service.impl;

import com.ugurcangursen.UserLoginRegister.advice.IMExceptionHandler;
import com.ugurcangursen.UserLoginRegister.dto.RegistrationRequest;
import com.ugurcangursen.UserLoginRegister.dto.UserDTO;
import com.ugurcangursen.UserLoginRegister.entity.User;
import com.ugurcangursen.UserLoginRegister.repository.UserRepository;
import com.ugurcangursen.UserLoginRegister.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(IMExceptionHandler.class); // Logging APIs

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserDTO save(UserDTO user) {
        User u = modelMapper.map(user, User.class);
        u = userRepository.save(u);
        user.setId(u.getId());
        return user;
    }

    @Override
    public UserDTO getById(Long id) {
        User u = userRepository.getOne(id);
        return modelMapper.map(u, UserDTO.class);
    }


    public List<UserDTO> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDTO[].class));
    }


    @Override
    public UserDTO getByUsername(String username) {
        User u = userRepository.findByUsername(username);
        return modelMapper.map(u, UserDTO.class);
    }

    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername());
            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }
}
