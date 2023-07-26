package com.example.vidhyarthi.repository;

import com.example.vidhyarthi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select * from course_details c where c.name= :name", nativeQuery = true)
    public List<Course> findByName(String name);

    @Query(value = "select * from course_details c where c.ENROLLED_YEAR= :year", nativeQuery = true)
    public List<Course> findByYear(String year);

    @Query(value = "select * from course_details c where c.NAME like :filter%", nativeQuery = true)
    public List<Course> findAllFilteredByName(String filter);

    @Query(value = "select * from course_details c where c.ENROLLED_YEAR like :filter%", nativeQuery = true)
    public List<Course> findAllFilteredByEnrolledYear(String filter);

}
