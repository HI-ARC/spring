package com.spring.hiarc.study.repository;

import com.spring.hiarc.study.entity.AttendanceList;
import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceListRepository extends JpaRepository<AttendanceList, Long> {
    List<AttendanceList> findByAttendanceCode(String code);
}
