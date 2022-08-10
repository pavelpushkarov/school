package com.example.school.controllers;

import com.example.school.dto.StudentDTO;
import com.example.school.dto.Result;
import com.example.school.entities.Student;
import com.example.school.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("students")
public class StudentsController {
    private final StudentsService studentsService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Result<Student>> getStudent(int studentId) {
        Result<Student> result = studentsService.getStudent(studentId);
        if (result.hasData()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result<Student>> addStudent(@RequestBody StudentDTO createStudent) {
        Result<Student> result = studentsService.addStudent(createStudent);
        if (result.hasData()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentsService.deleteStudent(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Result<Student>> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO request) {
        Result<Student> result = studentsService.updateStudent(id, request);
        if (result.hasData()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
