package com.spring.hiarc.user.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    @Size(min = 3, max = 25)
    private String username;

    private String password;
}
