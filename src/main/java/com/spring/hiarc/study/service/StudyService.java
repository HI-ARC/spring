package com.spring.hiarc.study.service;


import com.spring.hiarc.study.entity.Attendance;
import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.entity.UserStudy;
import com.spring.hiarc.study.repository.AttendanceRepository;
import com.spring.hiarc.study.repository.StudyRepository;
import com.spring.hiarc.study.repository.UserStudyRepository;
import com.spring.hiarc.user.entity.User;
import com.spring.hiarc.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final UserStudyRepository userStudyRepository;
    private final AttendanceRepository attendanceRepository;

    public StudyService(StudyRepository studyRepository, UserRepository userRepository, UserStudyRepository userStudyRepository, AttendanceRepository attendanceRepository) {
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
        this.userStudyRepository = userStudyRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public void makeStudy(String name) {
        if (studyRepository.existsByName(name)) {
            throw new RuntimeException("이미 존재하는 스터디명입니다.");
        }

        Study study = new Study();
        study.setName(name);
        studyRepository.save(study);
    }

    public String getStudy() {
        List<Study> studies = studyRepository.findAll();
        String studiesName = "";
        for (Study study : studies) {
            studiesName += study.getName() + " ";
        }
        return studiesName;
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

    public void attendanceStudy(String username, String code) {
        if (!studyRepository.existsByAttendanceCode(code)) {
            throw new RuntimeException("코드에 해당하는 스터디가 없습니다.");
        }
        Study study = studyRepository.findByAttendanceCode(code);

        if (!userRepository.existsByUsername(username)) {
            throw new RuntimeException("유효하지 않은 닉네임입니다.");
        }
        User user = userRepository.findByUsername(username);

        if (!userStudyRepository.existsByStudyAndUser(study, user)) {
            throw new RuntimeException("해당 유저가 스터디를 수강 중이지 않습니다.");
        }

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setStudy(study);
        attendance.setAttendanceTime(LocalDateTime.now());

        attendanceRepository.save(attendance);
    }

    public void setCode(String name, String code) {
        if (!studyRepository.existsByName(name)) {
            throw new RuntimeException("해당 스터디를 찾을 수 없습니다.");
        }

        Study study = studyRepository.findByName(name);
        study.setAttendanceCode(code);
        studyRepository.save(study);
    }
}
