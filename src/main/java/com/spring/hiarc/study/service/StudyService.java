package com.spring.hiarc.study.service;


import com.spring.hiarc.study.entity.Attendance;
import com.spring.hiarc.study.entity.AttendanceList;
import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.entity.StudyMembership;
import com.spring.hiarc.study.repository.AttendanceListRepository;
import com.spring.hiarc.study.repository.AttendanceRepository;
import com.spring.hiarc.study.repository.StudyMembershipRepository;
import com.spring.hiarc.study.repository.StudyRepository;
import com.spring.hiarc.user.entity.User;
import com.spring.hiarc.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceListRepository attendanceListRepository;
    private final StudyMembershipRepository studyMembershipRepository;

    public StudyService(StudyRepository studyRepository, UserRepository userRepository, AttendanceListRepository attendanceListRepository, AttendanceRepository attendanceRepository, StudyMembershipRepository studyMembershipRepository) {
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
        this.attendanceListRepository = attendanceListRepository;
        this.attendanceRepository = attendanceRepository;
        this.studyMembershipRepository = studyMembershipRepository;
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
        StringBuilder studiesName = new StringBuilder();
        for (Study study : studies) {
            studiesName.append(study.getName()).append(" ");
        }
        return studiesName.toString();
    }


    public void addStudyMember(String name, String username) {
        User user = userRepository.findByUsername(username);
        Study study = studyRepository.findByName(name);
        if (study == null) {
            throw new RuntimeException("존재하지 않는 스터디명입니다.");
        }
        if (user == null) {
            throw new RuntimeException("존재하지 않는 닉네임입니다.");
        }

        StudyMembership membership = new StudyMembership();
        membership.setUser(user);
        membership.setStudy(study);
        studyMembershipRepository.save(membership);

        user.getMemberships().add(membership);
        study.getMemberships().add(membership);
        userRepository.save(user);
        studyRepository.save(study);
    }

    public List<String> getMyStudy() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Set<StudyMembership> memberships = user.getMemberships();
//        System.out.println("getmystudy memberships size: " + memberships.size());
        List<String> studynames = new ArrayList<>();

        for (StudyMembership membership : memberships) {
            studynames.add(membership.getStudy().getName());
        }

        return studynames;
    }

    public void addAttendanceList(String name, String attendanceName, String code, String expiredTime) {
        Study study = studyRepository.findByName(name);
        if (study == null) {
            throw new RuntimeException("해당 스터디를 찾을 수 없습니다.");
        }


        AttendanceList attendanceList = new AttendanceList();
        attendanceList.setStudy(study);
        attendanceList.setAttendanceName(attendanceName);
        attendanceList.setStartTime(LocalDateTime.now());
        attendanceList.setAttendanceCode(code);
        attendanceList.setExpiredTime(Integer.parseInt(expiredTime));
        attendanceListRepository.save(attendanceList);

        Set<StudyMembership> memberships = study.getMemberships();
        for (StudyMembership membership : memberships) {
            User user = membership.getUser();
            Attendance attendance = new Attendance();
            attendance.setAttendanceList(attendanceList);
            attendance.setUser(user);
            attendance.setAttended(false);
            attendanceRepository.save(attendance);

            attendanceList.getAttendances().add(attendance);
        }

        study.getAttendanceLists().add(attendanceList);
        studyRepository.save(study);
    }

    public List<Attendance> getMyAttendance() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("알 수 없는 닉네임입니다.");
        }

        return attendanceRepository.findByUser(user);
    }





    public void attendanceStudy(String username, String code) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("알 수 없는 닉네임입니다.");
        }

        List<AttendanceList> attendanceLists = attendanceListRepository.findByAttendanceCode(code);
        System.out.println(attendanceLists.size());
        for (AttendanceList attendanceList : attendanceLists) {
            LocalDateTime endTime = attendanceList.getStartTime().plusMinutes(attendanceList.getExpiredTime());

            if (LocalDateTime.now().isBefore(endTime)) {
                Study study = attendanceList.getStudy();
                if (studyMembershipRepository.existsByUserAndStudy(user, study)) {
                    Set<Attendance> attendances = attendanceList.getAttendances();
                    System.out.println(attendances.size());
                    System.out.println(attendanceList.getAttendances().size());
                    System.out.println(attendanceList.getAttendanceName());
                    for (Attendance attendance : attendances) {
                        if (attendance.getUser().equals(user)) {
                            attendance.setAttended(true);
                            attendanceRepository.save(attendance);
                        }
                    }
                }
            }
        }
    }
}
