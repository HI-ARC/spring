package com.spring.hiarc.study.service;


import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.entity.UserStudy;
import com.spring.hiarc.study.repository.StudyRepository;
import com.spring.hiarc.study.repository.UserStudyRepository;
import com.spring.hiarc.user.entity.User;
import com.spring.hiarc.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final UserStudyRepository userStudyRepository;

    public StudyService(StudyRepository studyRepository, UserRepository userRepository, UserStudyRepository userStudyRepository) {
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
        this.userStudyRepository = userStudyRepository;
    }

    public void makeStudy(String name) {
        if (studyRepository.existsByName(name)) {
            throw new RuntimeException("이미 존재하는 스터디명입니다.");
        }

        Study study = new Study();
        study.setName(name);
        studyRepository.save(study);
    }

    public void addStudyMember(String name, String username) {
        if (!studyRepository.existsByName(name)) {
            throw new RuntimeException("존재하지 않는 스터디명입니다.");
        }
        if (!userRepository.existsByUsername(username)) {
            throw new RuntimeException("존재하지 않는 닉네임입니다.");
        }


        User user = userRepository.findByUsername(username);
        Study study = studyRepository.findByName(name);
        if (userStudyRepository.existsByStudyAndUser(study, user)) {
            throw new RuntimeException("이미 수강 중입니다.");
        }

        UserStudy userStudy = new UserStudy();
        userStudy.setStudy(study);
        userStudy.setUser(user);
        userStudyRepository.save(userStudy);

        user.getUserStudies().add(userStudy);
        study.getUserStudies().add(userStudy);
        userRepository.save(user);
        studyRepository.save(study);
    }

    public List<String> getMyStudy(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = userRepository.findByUsername(username);

        return user.getUserStudies().stream()
                .map(UserStudy::getStudy)
                .map(Study::getName)
                .collect(Collectors.toList());
    }
}
