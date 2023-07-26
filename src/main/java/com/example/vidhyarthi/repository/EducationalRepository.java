package com.example.vidhyarthi.repository;

import com.example.vidhyarthi.entity.EducationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalRepository extends JpaRepository<EducationDetails, Long> {

    @Query(value = "select * from  educational_details where STUDENT_ID= :id", nativeQuery = true)
    public List<EducationDetails> findAllByStudentId(Long id);
}
