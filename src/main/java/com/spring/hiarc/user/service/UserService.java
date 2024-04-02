package com.spring.hiarc.user.service;


import com.spring.hiarc.user.entity.User;
import com.spring.hiarc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signUp(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUserRole("USER");
        userRepository.save(user);

        return user;
    }

    public User signIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("존재하지 않는 아이디입니다.");
        }

        if (!Objects.equals(user.getPassword(), bCryptPasswordEncoder.encode(password))) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }


    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
