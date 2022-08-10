package com.example.school.controllers;

import com.example.school.dto.Result;
import com.example.school.dto.CourseDTO;
import com.example.school.entities.Course;
import com.example.school.service.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("courses")
public class CoursesController {
    private final CoursesService coursesService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Result<Course>> getCourse(int courseId) {
        Result<Course> result = coursesService.getCourse(courseId);
        if (result.hasData()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result<Course>> addCourse(@RequestBody CourseDTO createCourse) {
        Result<Course> result = coursesService.addCourse(createCourse);
        if (result.hasData()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        coursesService.deleteCourse(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Result<Course>> updateCourse(@PathVariable Integer id, @RequestBody CourseDTO request) {
        Result<Course> result = coursesService.updateCourse(id, request);
        if (result.hasData()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
