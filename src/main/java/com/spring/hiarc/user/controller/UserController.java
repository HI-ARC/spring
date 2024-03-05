package com.spring.hiarc.user.controller;

import com.spring.hiarc.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            userService.signUp(username, password);
            return ResponseEntity.ok("회원가입 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            userService.signIn(username, password);
            return ResponseEntity.ok("로그인 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/info")
    public String info() {
        try {
            return userService.getUserInfo();
        }
        catch (Exception e) {
            return "info worked";
        }
    }
}
