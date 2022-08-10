package com.example.school.service;

import com.example.school.dto.StudentDTO;
import com.example.school.dto.Result;
import com.example.school.entities.Student;
import com.example.school.repositories.StudentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    @Override
    public List<Student> findStudentsByCourse(int courseId) {
        return studentsRepository.findByCourseId(courseId);
    }

    @Override
    public List<Student> findStudentsWithoutAnyCourses() {
        return studentsRepository.findWithoutAnyCourses();
    }

    @Override
    public Result<Student> getStudent(int studentId) {
        Optional<Student> foundStudent = studentsRepository.findById(studentId);
        return foundStudent
                .map(Result::ok)
                .orElseGet(() -> Result.error("Student with id doesn't exist"));

    }

    @Override
    public Result<Student> addStudent(StudentDTO createStudent) {
        String name = createStudent.getName();
        if (name == null || name.isEmpty()) {
            return Result.error("Name can not be empty");
        }

        Student newStudent = new Student();
        newStudent.setName(name);

        Student student = studentsRepository.save(newStudent);

        return Result.ok(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public Result<Student> updateStudent(Integer id, StudentDTO request) {
        Optional<Student> foundStudent = studentsRepository.findById(id);
        if (!foundStudent.isPresent()) {
            return Result.error("Student with id doesn't exist");
        }

        String name = request.getName();
        if (name == null || name.isEmpty()) {
            return Result.error("Name can not be empty");
        }

        Student student = foundStudent.get();
        student.setName(name);

        student = studentsRepository.save(student);

        return Result.ok(student);
    }
}
