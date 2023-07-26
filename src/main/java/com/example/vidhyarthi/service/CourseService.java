package com.example.vidhyarthi.service;

import com.example.vidhyarthi.Request.CourseRequest;
import com.example.vidhyarthi.Response.ResponseObject;
import com.example.vidhyarthi.dto.Studentdetails;

import java.util.List;


public interface CourseService {
    public ResponseObject saveCourse(CourseRequest course);

    public List<Studentdetails> getStudentDetailsByCourse(String courseName);

    public List<Studentdetails> getStudentDetailsByEnrollmentDate(String enrolledYear);

    public List<Studentdetails> getFilteredStudentDetailsByCourseName(String name);

    public List<Studentdetails> getFilteredStudentDetailsByEnrolledYear(String name);
}
