package com.example.school.service;

import com.example.school.dto.CourseDTO;
import com.example.school.dto.RegisterDTO;
import com.example.school.dto.Result;
import com.example.school.entities.Course;
import com.example.school.entities.Student;
import com.example.school.repositories.CoursesRepository;
import com.example.school.repositories.StudentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    private final StudentsRepository studentsRepository;

    @Override
    public List<Course> findCoursesByStudent(int studentId) {
        return coursesRepository.findByStudentId(studentId);
    }

    @Override
    public List<Course> findCoursesWithoutAnyCourses() {
        return coursesRepository.findWithoutAnyStudents();
    }

    @Override
    public Result<Course> getCourse(int courseId) {
        Optional<Course> foundCourse = coursesRepository.findById(courseId);
        return foundCourse
                .map(Result::ok)
                .orElseGet(() -> Result.error("Course with id doesn't exist"));

    }

    @Override
    public Result<Course> addCourse(CourseDTO createCourse) {
        String name = createCourse.getName();
        if (name == null || name.isEmpty()) {
            return Result.error("Name can not be empty");
        }

        Course newCourse = new Course();
        newCourse.setName(name);

        Course course = coursesRepository.save(newCourse);

        return Result.ok(course);
    }

    @Override
    public void deleteCourse(Integer id) {
        coursesRepository.deleteById(id);
    }

    @Override
    public Result<Course> updateCourse(Integer id, CourseDTO request) {
        Optional<Course> foundCourse = coursesRepository.findById(id);
        if (!foundCourse.isPresent()) {
            return Result.error("Course with id doesn't exist");
        }

        String name = request.getName();
        if (name == null || name.isEmpty()) {
            return Result.error("Name can not be empty");
        }

        Course course = foundCourse.get();
        course.setName(name);

        course = coursesRepository.save(course);

        return Result.ok(course);
    }

    @Override
    public Result<Integer> register(Integer id, RegisterDTO register) {
        if (register.getCourseIds() == null || register.getCourseIds().isEmpty()) {
            return Result.error("Should be at least 1 course");
        }

        Optional<Student> foundStudent = studentsRepository.findById(id);
        if (!foundStudent.isPresent()) {
            return Result.error("Student with id doesn't exist");
        }

        Student student = foundStudent.get();
        Set<Course> studentCourses = student.getCourses();
        int studentCoursesCount = studentCourses.size();
        int newCoursesCount = 0;
        Iterable<Course> newCourses = coursesRepository.findAllById(register.getCourseIds());
        for (Course course : newCourses) {
            if (course.getStudents().size() >= 50) {
                continue;
            }

            if (!studentCourses.contains(course)) {
                newCoursesCount++;
                studentCourses.add(course);
            }
        }

        if (studentCoursesCount + newCoursesCount <= 5) {
            studentsRepository.save(student);
            return Result.ok(newCoursesCount);
        }

        return Result.error("Bad request");
    }
}
