package com.spring.hiarc.study.repository;

import com.spring.hiarc.study.entity.UserAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttendanceRepository extends JpaRepository<UserAttendance, Long> {
}
