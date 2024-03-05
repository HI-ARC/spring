package com.spring.hiarc.study.repository;

import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.entity.UserStudy;
import com.spring.hiarc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudy, Long> {
    boolean existsByStudyAndUser(Study study, User user);
}
