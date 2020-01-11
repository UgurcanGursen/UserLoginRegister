package com.ugurcangursen.UserLoginRegister.repository;

import com.ugurcangursen.UserLoginRegister.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}