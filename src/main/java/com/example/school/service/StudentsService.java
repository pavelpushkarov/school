package com.example.school.service;

import com.example.school.dto.Result;
import com.example.school.dto.StudentDTO;
import com.example.school.entities.Student;

import java.util.List;

public interface StudentsService {

    List<Student> findStudentsByCourse(int courseId);

    List<Student> findStudentsWithoutAnyCourses();

    Result<Student> getStudent(int courseId);

    Result<Student> addStudent(StudentDTO createStudent);

    void deleteStudent(Integer id);

    Result<Student> updateStudent(Integer id, StudentDTO request);
}
