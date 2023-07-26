package com.example.vidhyarthi.controller;

import com.example.vidhyarthi.repository.CourseRepository;
import com.example.vidhyarthi.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    private CourseRepository courseRepository;


//    @PostMapping("/save")
//    public ResponseEntity saveCourse(@Valid @RequestBody CourseRequest course) {
//        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/name/{name}")
//    public ResponseEntity<List<Studentdetails>> getStudentDetailsFromCourseName(@PathVariable("name") String name) {
//        return new ResponseEntity<>(courseService.getStudentDetailsByCourse(name), HttpStatus.FOUND);
//    }
//
//    @GetMapping("/year/{year}")
//    public ResponseEntity<List<Studentdetails>> getStudentDetailsByEnrolledYear(@PathVariable("year") String year) {
//        return new ResponseEntity<>(courseService.getStudentDetailsByEnrollmentDate(year), HttpStatus.FOUND);
//    }
}
