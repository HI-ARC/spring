package com.spring.hiarc.study.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "attendance_list")
public class AttendanceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    private String attendanceName;

    private LocalDateTime startTime;

    private Integer expiredTime;

    private String attendanceCode;

    @OneToMany
    private Set<Attendance> attendances = new HashSet<>();
}
