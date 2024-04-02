package com.spring.hiarc.study.repository;

import com.spring.hiarc.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
    boolean existsByName(String name);
    Study findByName(String name);

}
