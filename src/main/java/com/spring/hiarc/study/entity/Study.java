package com.spring.hiarc.study.entity;


import com.spring.hiarc.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;


    @OneToMany(mappedBy = "study", fetch = FetchType.EAGER)
    private Set<StudyMembership> memberships = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<AttendanceList> attendanceLists = new HashSet<>();
}
