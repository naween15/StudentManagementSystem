package com.example.vidhyarthi.serviceImpl;

import com.example.vidhyarthi.Exceptions.ResourceNotFoundException;
import com.example.vidhyarthi.Request.CourseRequest;
import com.example.vidhyarthi.Response.ResponseObject;
import com.example.vidhyarthi.dto.Studentdetails;
import com.example.vidhyarthi.entity.Course;
import com.example.vidhyarthi.entity.Department;
import com.example.vidhyarthi.entity.Student;
import com.example.vidhyarthi.repository.CourseRepository;
import com.example.vidhyarthi.repository.DepartmentRepository;
import com.example.vidhyarthi.repository.StudentRepository;
import com.example.vidhyarthi.service.CourseService;
import com.example.vidhyarthi.util.ResponseUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private DepartmentRepository departmentRepository;
    private StudentRepository studentRepository;

    @Override
    public ResponseObject saveCourse(CourseRequest courseDto) {
        try {
            Course course = new Course();
            course.setName(courseDto.getName());
            course.setEnrolledYear(courseDto.getEnrolledYear());
            course.setDuration(courseDto.getDuration());
            Department department = departmentRepository.findByName(courseDto.getDepartmentName());
            if (department == null) {
                return ResponseUtility.resourceNotFound(course.getDepartment(), "Department not found", HttpStatus.OK);
            }
            course.setDepartment(department);
            return ResponseUtility.resourceCreated(course, course.getName() + " created", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceNotCreated(null, courseDto.getName() + " couldnt be created", HttpStatus.OK);
        }
    }

    @Override
    public List<Studentdetails> getStudentDetailsByCourse(String courseName) {
        try {
            List<Course> courses = courseRepository.findByName(courseName);
            List<Studentdetails> students = new ArrayList<>();
            for (Course eachMatchingCourse : courses) {
                List<Student> studentList = studentRepository.findAllByCourseId(eachMatchingCourse.getId());
                for (Student student : studentList) {
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
                    studentdetails.setModifiedDate(student.getModifiedDate().toString());
                    students.add(studentdetails);
                }
            }
            return students;
        } catch (Exception e) {
            throw new ResourceNotFoundException(" no user with course name:" + courseName + "found");
        }

    }

    @Override
    public List<Studentdetails> getStudentDetailsByEnrollmentDate(String enrolledYear) {
        try {
            List<Course> courses = courseRepository.findByYear(enrolledYear);
//            if (courses.isEmpty()) {
//                throw new ResourceNotFoundException("No course with matching year found");
//            }
            List<Studentdetails> students = new ArrayList<>();
            if (courses.isEmpty()) {
                System.out.println("empty");
            }
            for (Course eachMatchingCourse : courses) {
                List<Student> studentList = studentRepository.findAllByCourseId(eachMatchingCourse.getId());
                for (Student student : studentList) {
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
                    studentdetails.setModifiedDate(student.getModifiedDate().toString());
                    students.add(studentdetails);
                }
            }
//            List<Studentdetails> studentdetailsList = students.stream().filter(student -> student.getStatus().equalsIgnoreCase("ACTIVE") || student.getStatus().equalsIgnoreCase("INACTIVE")).collect(Collectors.toList());
            return students;
        } catch (Exception e) {
            throw new ResourceNotFoundException("No course with matching year found");
        }
    }

    @Override
    public List<Studentdetails> getFilteredStudentDetailsByCourseName(String name) {
        int i = 1;
        try {
            List<Course> courses = courseRepository.findAllFilteredByName(name);
            List<Studentdetails> students = new ArrayList<>();
            if (courses.isEmpty()) {
                System.out.println("empty");
            }

            for (Course eachMatchingCourse : courses) {

                System.out.println(eachMatchingCourse.getId() + "------------");

                List<Student> studentList = studentRepository.findAllByCourseId(eachMatchingCourse.getId());

                if (studentList.isEmpty()) {
                    continue;
//                    throw new ResourceNotFoundException("No student with matching course found");
                }
                for (Student student : studentList) {
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
                    students.add(studentdetails);
                }
            }
            List<Studentdetails> studentdetailsList = students.stream().filter(student -> student.getStatus().equalsIgnoreCase("ACTIVE") || student.getStatus().equalsIgnoreCase("INACTIVE")).collect(Collectors.toList());
            return studentdetailsList;
        } catch (Exception e) {
            throw new ResourceNotFoundException("No student with matching course name found");
        }
    }

    @Override
    public List<Studentdetails> getFilteredStudentDetailsByEnrolledYear(String year) {
        try {
            List<Course> courses = courseRepository.findAllFilteredByEnrolledYear(year);
            List<Studentdetails> students = new ArrayList<>();
            if (courses.isEmpty()) {
                System.out.println("empty");
            }
            for (Course eachMatchingCourse : courses) {
                List<Student> studentList = studentRepository.findAllByCourseId(eachMatchingCourse.getId());
                System.out.println("Total matched by course name is" + studentList.size());
                if (studentList.isEmpty()) {
                    System.out.println("empty2");
                }
                for (Student student : studentList) {
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
                    students.add(studentdetails);
                }
            }
            List<Studentdetails> studentdetailsList = students.stream().filter(student -> student.getStatus().equalsIgnoreCase("ACTIVE") || student.getStatus().equalsIgnoreCase("INACTIVE")).collect(Collectors.toList());
            return studentdetailsList;
        } catch (Exception e) {
            throw new ResourceNotFoundException("no student with the provided enrolled year found");
        }

    }
}
