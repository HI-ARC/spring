package com.spring.hiarc.user.repository;

import com.spring.hiarc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    User findByUsernameAndPassword(String username, String password);

}
