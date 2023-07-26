package com.example.vidhyarthi.controller;

import com.example.vidhyarthi.dto.Studentdetails;
import com.example.vidhyarthi.service.CourseService;
import com.example.vidhyarthi.service.DepartmentService;
import com.example.vidhyarthi.service.SearchFilterService;
import com.example.vidhyarthi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/filter")
public class FilterController {

    private StudentService studentService;
    private CourseService courseService;
    private DepartmentService departmentService;
    private SearchFilterService searchFilterService;


    //    ------------------------------done---------------------------------------
    @GetMapping("/{search}")
    public HashSet<Studentdetails> getFilteredStudents(@PathVariable(required = false) String searchQuery) {
        List<Studentdetails> filteredStudentByCourse = courseService.getFilteredStudentDetailsByCourseName(searchQuery);
        List<Studentdetails> filteredStudentByName = searchFilterService.getFilteredStudents(searchQuery);
        List<Studentdetails> filteredStudentByEnrolledYear = courseService.getFilteredStudentDetailsByEnrolledYear(searchQuery);
        List<Studentdetails> list = new ArrayList<>();
        list.addAll(filteredStudentByCourse);
        list.addAll(filteredStudentByName);
        list.addAll(filteredStudentByEnrolledYear);
        HashSet<Studentdetails> studentdetails = new HashSet<>(list);
        return studentdetails;
    }

}
