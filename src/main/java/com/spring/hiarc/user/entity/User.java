package com.spring.hiarc.user.entity;

import com.spring.hiarc.study.entity.UserStudy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String userRole;

    @OneToMany(mappedBy = "user")
    private List<UserStudy> userStudies = new ArrayList<>();
}
