package com.example.school.repositories;

import com.example.school.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Integer> {

    @Query(value = "SELECT s FROM Student s INNER JOIN s.courses c WHERE c.id = ?1")
    List<Student> findByCourseId(int courseId);

    @Query(value = "SELECT s FROM Student s LEFT JOIN s.courses c WHERE c.id is null")
    List<Student> findWithoutAnyCourses();
}
