package com.spring.hiarc.user.entity;

import com.spring.hiarc.study.entity.Attendance;
import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.entity.StudyMembership;
import com.spring.hiarc.study.service.StudyService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    private Set<StudyMembership> memberships = new HashSet<>();

}
