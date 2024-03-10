package com.spring.hiarc.study.repository;


import com.spring.hiarc.study.entity.Attendance;
import com.spring.hiarc.study.entity.StudyAttendance;
import com.spring.hiarc.study.entity.UserAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}

