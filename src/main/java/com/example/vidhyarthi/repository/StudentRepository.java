package com.example.vidhyarthi.repository;

import com.example.vidhyarthi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select * from student s ", nativeQuery = true)
    public List<Student> findAllData();

    @Query(value = "select * from student s where COURSE_ID=:id", nativeQuery = true)
    public List<Student> findAllByCourseId(Long id);

    @Query(value = "select * from student s where FIRST_NAME= :name", nativeQuery = true)
    public List<Student> findAllByName(String name);

    @Query(value = "select * from student s where FIRST_NAME like :filter%", nativeQuery = true)
    public List<Student> findAllFiltered(String filter);

    @Query(value = "select * from student s where s.MOBILE_NUMBER= :contact", nativeQuery = true)
    public Student findByContact(String contact);
}
