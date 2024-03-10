package com.spring.hiarc.study.entity;

import com.spring.hiarc.study.repository.AttendanceRepository;
import com.spring.hiarc.user.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;


@Embeddable
public class UserAttendanceId implements Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private Attendance attendance;

}
