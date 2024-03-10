package com.spring.hiarc.study.repository;

import com.spring.hiarc.study.entity.StudyAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyAttendanceRepository extends JpaRepository<StudyAttendance, Long> {
}
