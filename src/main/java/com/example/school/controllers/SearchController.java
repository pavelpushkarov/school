package com.example.school.controllers;

import com.example.school.entities.Course;
import com.example.school.entities.Student;
import com.example.school.service.CoursesService;
import com.example.school.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("search")
public class SearchController {

    private final StudentsService studentsService;
    private final CoursesService coursesService;

    @GetMapping(path = "findStudentByCourseId/{id}")
    public ResponseEntity<List<Student>> findStudentsByCourse(int courseId) {
        List<Student> students = studentsService.findStudentsByCourse(courseId);
        return ResponseEntity.ok(students);
    }

    @GetMapping(path = "/findCourseByStudentId/{id}")
    public List<Course> findCourseWithSpecificStudent(int studentId) {
        return coursesService.findCoursesByStudent(studentId);
    }

    @GetMapping(path = "/findCoursesWithoutAnyStudents")
    public List<Course> findCoursesWithoutAnyStudents() {
        return coursesService.findCoursesWithoutAnyCourses();
    }

    @GetMapping(path = "/findStudentsWithoutAnyCourses")
    public List<Student> findStudentsWithoutAnyCourses() {
        return studentsService.findStudentsWithoutAnyCourses();
    }
}
