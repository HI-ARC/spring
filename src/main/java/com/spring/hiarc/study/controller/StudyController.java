package com.spring.hiarc.study.controller;

import com.spring.hiarc.study.entity.Study;
import com.spring.hiarc.study.service.StudyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(203).body("오류 발생: " + e.getMessage());
        }
    }

    @PostMapping("/addstudymember")
    public ResponseEntity<String> addStudyMember(@RequestParam("name") String name, @RequestParam("username") String username) {
        try {
            studyService.addStudyMember(name, username);
            return ResponseEntity.ok("스터디 멤버 추가 성공!");
        }
        catch (Exception e) {
            return ResponseEntity.status(203).body("오류 발생: " + e.getMessage());
        }
    }

    @GetMapping("/getmystudy")
    public ResponseEntity<List<String>> getMyStudy(@RequestParam("username") String username) {
        try {
            return ResponseEntity.ok(studyService.getMyStudy(username));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<String>());
        }
    }
}
