package com.spring.hiarc.user;

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
    public ResponseEntity<String> signUp(@RequestBody SignDTO signDTO) {
        try {
            userService.signUp(signDTO.getUsername(), signDTO.getPassword());
            return ResponseEntity.ok("회원가입 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignDTO signDTO) {
        try {
            userService.signIn(signDTO.getUsername(), signDTO.getPassword());
            return ResponseEntity.ok("로그인 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
