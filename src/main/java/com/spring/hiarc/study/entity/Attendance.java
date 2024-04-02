package com.spring.hiarc.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.hiarc.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private AttendanceList attendanceList;

    @ManyToOne
    @JsonIgnore
    private User user;

    private boolean isAttended;

    public String getUsername() {
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }

    public String getStudyName() {
        if (attendanceList != null && attendanceList.getStudy() != null) {
            return attendanceList.getStudy().getName();
        }
        return null;
    }

    public String getAttendanceName() {
        if (attendanceList != null) {
            return attendanceList.getAttendanceName();
        }
        return null;
    }
}
