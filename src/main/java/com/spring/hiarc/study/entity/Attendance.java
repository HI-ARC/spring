package com.spring.hiarc.study.entity;

import com.spring.hiarc.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Study study;

    @Column(name = "attendance_time")
    private LocalDateTime attendanceTime;
}
