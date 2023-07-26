package com.example.vidhyarthi.serviceImpl;

import com.example.vidhyarthi.Exceptions.ResourceNotFoundException;
import com.example.vidhyarthi.dto.Studentdetails;
import com.example.vidhyarthi.entity.Student;
import com.example.vidhyarthi.repository.StudentRepository;
import com.example.vidhyarthi.service.CourseService;
import com.example.vidhyarthi.service.DepartmentService;
import com.example.vidhyarthi.service.SearchFilterService;
import com.example.vidhyarthi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchFilterImplementation implements SearchFilterService {
    private StudentService studentService;
    private CourseService courseService;
    private DepartmentService departmentService;
    private StudentRepository studentRepository;


    @Override
    public List<Studentdetails> getFilteredStudents(String filter) {
//        System.out.println(filter);
        try {
            List<Studentdetails> studentsDetails = new ArrayList<>();
            List<Student> students = studentRepository.findAllFiltered(filter);
//            if (students.isEmpty()) {
//                throw new ResourceNotFoundException("No any matching student found");
//            }
//            else {
            for (Student student : students) {
                Studentdetails studentdetails = new Studentdetails();
                studentdetails.setCourse(student.getCourse().getName());
                studentdetails.setContact(student.getMobileNumber());
                studentdetails.setDepartment(student.getCourse().getDepartment().getName());
                studentdetails.setGender(student.getGender().toString());
                studentdetails.setName(student.getFirstName());
                studentdetails.setMaritalStatus(student.getMaritalStatus());
                studentdetails.setEnrolledYear(student.getCourse().getEnrolledYear());
                studentdetails.setAddedBy(student.getAddedBy());
                studentdetails.setModifiedBy(student.getModifiedBy());
                studentdetails.setStatus(student.getStatus());
                studentdetails.setModifiedDate(student.getModifiedDate().toString());
                studentsDetails.add(studentdetails);

            }
//            }
            List<Studentdetails> studentdetailsList = studentsDetails.stream().filter(student -> student.getStatus().equalsIgnoreCase("ACTIVE") || student.getStatus().equalsIgnoreCase("INACTIVE")).collect(Collectors.toList());
            return studentdetailsList;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Sorry no any matching user details found");
        }
    }
}
