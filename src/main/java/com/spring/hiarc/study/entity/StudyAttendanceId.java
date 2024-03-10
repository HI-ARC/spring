package com.spring.hiarc.study.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class StudyAttendanceId implements Serializable {
    @ManyToOne
    private Study study;

    @ManyToOne Attendance attendance;
}
