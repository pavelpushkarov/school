package com.example.school.repositories;

import com.example.school.entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Integer> {

    @Query(value = "SELECT c FROM Course c INNER JOIN c.students s WHERE s.id = ?1")
    List<Course> findByStudentId(int studentId);

    @Query(value = "SELECT c FROM Course c LEFT JOIN c.students s WHERE s.id is null")
    List<Course> findWithoutAnyStudents();
}
