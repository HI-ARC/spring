package com.spring.hiarc.user;


import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User signUp(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return userRepository.save(user);
    }

    public void signIn(String username, String password) {
        if (userRepository.findByUsername(username) == null) {
            throw new RuntimeException("존재하지 않는 아이디입니다.");
        }

        User user = userRepository.findByUsername(username);
        if (!Objects.equals(user.getPassword(), password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }



    }

}
