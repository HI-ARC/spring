package com.spring.hiarc.study.repository;

import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.entity.StudyMembership;
import com.spring.hiarc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyMembershipRepository extends JpaRepository<StudyMembership, Long> {
    boolean existsByUserAndStudy(User user, Study study);
}
