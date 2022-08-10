package com.example.school.service;

import com.example.school.dto.CourseDTO;
import com.example.school.dto.RegisterDTO;
import com.example.school.dto.Result;
import com.example.school.entities.Course;
import com.example.school.repositories.CoursesRepository;
import com.example.school.repositories.StudentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

class CoursesServiceImplTest {
    @Mock
    CoursesRepository coursesRepository;
    @Mock
    StudentsRepository studentsRepository;
    @InjectMocks
    CoursesServiceImpl coursesServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCoursesByStudent() {
        Course course = new Course();
        course.setName("Math");
        when(coursesRepository.findByStudentId(anyInt())).thenReturn(List.of(course));

        List<Course> result = coursesServiceImpl.findCoursesByStudent(0);
        Assertions.assertEquals("Math", result.get(0).getName());
    }

    @Test
    void testFindCoursesWithoutAnyCourses() {
        Course course = new Course();
        course.setName("Math");
        when(coursesRepository.findWithoutAnyStudents()).thenReturn(List.of(course));

        List<Course> result = coursesServiceImpl.findCoursesWithoutAnyCourses();
        Assertions.assertEquals("Math", result.get(0).getName());
    }

    @Test
    void testGetCourse() {
        Course course = new Course();
        course.setName("Math");
        when(coursesRepository.findById(anyInt())).thenReturn(Optional.of(course));

        Result<Course> result = coursesServiceImpl.getCourse(0);

        Assertions.assertEquals("Math", result.getData().getName());
    }

    @Test
    void testAddCourse() {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Math");
        Course course = new Course();
        course.setId(1);
        course.setName("Math");
        when(coursesRepository.save(any())).thenReturn(course);

        Result<Course> result = coursesServiceImpl.addCourse(courseDTO);

        Course resultCourse = result.getData();
        Assertions.assertEquals(1, resultCourse.getId());
        Assertions.assertEquals("Math", resultCourse.getName());
    }
}
