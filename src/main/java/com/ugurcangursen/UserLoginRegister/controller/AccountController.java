package com.ugurcangursen.UserLoginRegister.controller;

import com.ugurcangursen.UserLoginRegister.dto.LoginRequest;
import com.ugurcangursen.UserLoginRegister.dto.RegistrationRequest;
import com.ugurcangursen.UserLoginRegister.dto.TokenResponse;
import com.ugurcangursen.UserLoginRegister.entity.User;
import com.ugurcangursen.UserLoginRegister.repository.UserRepository;
import com.ugurcangursen.UserLoginRegister.security.JwtTokenUtil;
import com.ugurcangursen.UserLoginRegister.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AccountController {


    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;
    private UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        Boolean response = userService.register(registrationRequest);
        return ResponseEntity.ok(response);
    }

}
