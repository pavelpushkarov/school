package com.example.school.service;

import com.example.school.dto.CourseDTO;
import com.example.school.dto.RegisterDTO;
import com.example.school.dto.Result;
import com.example.school.entities.Course;

import java.util.List;

public interface CoursesService {

    List<Course> findCoursesByStudent(int studentId);

    List<Course> findCoursesWithoutAnyCourses();

    Result<Course> getCourse(int courseId);

    Result<Course> addCourse(CourseDTO createCourse);

    void deleteCourse(Integer id);

    Result<Course> updateCourse(Integer id, CourseDTO request);

    Result<Integer> register(Integer id, RegisterDTO register);
}
