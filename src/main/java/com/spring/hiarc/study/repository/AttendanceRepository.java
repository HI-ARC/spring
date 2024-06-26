package com.spring.hiarc.study.repository;


import com.spring.hiarc.study.entity.Attendance;
import com.spring.hiarc.study.entity.AttendanceList;
import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUser(User user);
}

