package com.spring.hiarc.study.entity;

import com.spring.hiarc.user.entity.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAttendance {
    @EmbeddedId
    private UserAttendanceId id;

}
