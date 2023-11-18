package com.example.BossaBox.repository;

import com.example.BossaBox.domain.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    //query
    UserDetails findByUsername(String username);
}
