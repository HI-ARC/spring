package com.spring.hiarc.study.controller;

import com.spring.hiarc.study.entity.Attendance;
import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.service.StudyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.TableView;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @PostMapping("/makestudy")
    public ResponseEntity<String> makeStudy(@RequestParam("name") String name) {
        try {
            studyService.makeStudy(name);
            return ResponseEntity.ok("스터디 생성 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("오류 발생: " + e.getMessage());
        }
    }

    @PostMapping("/addstudymember")
    public ResponseEntity<String> addStudyMember(@RequestParam("name") String name, @RequestParam("username") String username) {
        try {
            studyService.addStudyMember(name, username);
            return ResponseEntity.ok("스터디 멤버 추가 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("오류 발생: " + e.getMessage());
        }
    }

    @PostMapping("/addAttendanceList")
    public ResponseEntity<String> addAttendanceList(@RequestParam("name") String name, @RequestParam("attendanceName") String attendanceName, @RequestParam("code") String code, @RequestParam("expiredTime") String expiredTime) {
        try {
            studyService.addAttendanceList(name, attendanceName, code, expiredTime);
            return ResponseEntity.ok("코드 변경 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("오류 발생: " + e.getMessage());
        }
    }

    @GetMapping("/getmystudy")
    public ResponseEntity<List<String>> getMyStudy() {
        try {
            return ResponseEntity.ok(studyService.getMyStudy());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<String>());
        }
    }

    @GetMapping("/getstudy")
    public ResponseEntity<String> getStudy() {
        try {
            return ResponseEntity.ok(studyService.getStudy());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("오류 발생: " + e.getMessage());
        }
    }

    @GetMapping("/getmyattendance")
    public ResponseEntity<?> getMyAttendance() {
        try {
            return ResponseEntity.ok(studyService.getMyAttendance());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("오류 발생: " + e.getMessage());
        }
    }




    @PostMapping("/attendance")
    public ResponseEntity<String> attendanceStudy(@RequestParam("username") String username, @RequestParam("code") String code) {
        try {
            studyService.attendanceStudy(username, code);
            return ResponseEntity.ok("출석 체크 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("오류 발생: " + e.getMessage());
        }
    }
}
