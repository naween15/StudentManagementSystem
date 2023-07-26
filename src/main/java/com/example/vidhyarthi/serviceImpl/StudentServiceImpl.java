package com.example.vidhyarthi.serviceImpl;

import com.example.vidhyarthi.Exceptions.ResourceNotFoundException;
import com.example.vidhyarthi.Request.RegistrationRequest;
import com.example.vidhyarthi.Response.ResponseObject;
import com.example.vidhyarthi.config.JwtAuthenticationFilter;
import com.example.vidhyarthi.dto.Studentdetails;
import com.example.vidhyarthi.entity.Course;
import com.example.vidhyarthi.entity.Department;
import com.example.vidhyarthi.entity.EducationDetails;
import com.example.vidhyarthi.entity.Student;
import com.example.vidhyarthi.payload.EducationQualificationPayload;
import com.example.vidhyarthi.payload.StudentPayload;
import com.example.vidhyarthi.repository.*;
import com.example.vidhyarthi.service.StudentService;
import com.example.vidhyarthi.util.ResponseUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private EducationalRepository educationalRepository;
    private DepartmentRepository departmentRepository;
    private CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseObject saveStudent(RegistrationRequest student) {
        try {
            Student newStudent = new Student();
            newStudent.setAddress(student.getAddress());
            newStudent.setGender(student.getGender());
            newStudent.setCitizenshipNumber(student.getCitizenshipNumber());
            newStudent.setDateOfBirth(student.getDateOfBirth());
            newStudent.setFirstName(student.getFirstName());
            newStudent.setMaritalStatus(student.getMaritalStatus());
            newStudent.setLastName(student.getLastName());
            newStudent.setMiddleName(student.getMiddleName());
            newStudent.setMobileNumber(student.getMobileNumber());
            newStudent.setEmailAddress(student.getEmailAddress());
            newStudent.setAddedBy(JwtAuthenticationFilter.name);
            newStudent.setModifiedBy("Not modified yet");
            newStudent.setStatus("ACTIVE");
            List<Course> availableCourses = courseRepository.findByName(student.getCourseName());
            if (availableCourses.isEmpty()) {

                return ResponseUtility.resourceNotFound(student, "The course student is trying to study is not found", HttpStatus.OK);
            }
            for (Course course : availableCourses) {
                if (course.getEnrolledYear().equals(student.getEnrolledYear())) {
                    newStudent.setCourse(course);
                }
            }
            studentRepository.save(newStudent);
            Set<EducationDetails> educational_details = new HashSet<>();
            educational_details.addAll(student.getEducational_details());
            for (EducationDetails educational_detail : educational_details) {
                educational_detail.setStudent(newStudent);
            }
            educationalRepository.saveAll(educational_details);
            return ResponseUtility.resourceCreated(student, newStudent.getFirstName() + " created", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceNotCreated(student, student.getFirstName() + " couldnt be saved", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseObject getStudentDetails() {
        try {
            List<Student> students = studentRepository.findAllData();
            List<Studentdetails> studentsdetails = new ArrayList<>();
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
                studentdetails.setModifiedDate(student.getModifiedDate().toString().substring(0, 10));
                if (student.getStatus().equalsIgnoreCase("DELETED")) {
                    continue;
                }
                studentsdetails.add(studentdetails);
            }

            return ResponseUtility.resourceFound(studentsdetails, "Students found", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceNotFound("Something went wrong", "Students couldnt be found", HttpStatus.CONFLICT);
        }
    }


    @Override
    public ResponseObject getStudentDetailsByName(String name) {
        try {
            List<Student> students = studentRepository.findAllByName(name);
            List<Studentdetails> studentsdetails = new ArrayList<>();
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
                studentdetails.setStatus(student.getStatus());
                studentdetails.setModifiedBy(student.getModifiedBy());
                studentdetails.setModifiedDate(student.getModifiedDate().toString().substring(0, 10));
                studentsdetails.add(studentdetails);
            }
            return ResponseUtility.resourceFound(studentsdetails, "Students fetched", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("no student with name:" + name + " found");
        }
    }

    @Override
    public ResponseObject getStudentByContact(String contact) {
        try {
            Student student = studentRepository.findByContact(contact);
            Course course = courseRepository.findById(student.getCourse().getId()).get();
            Department department = departmentRepository.findById(course.getDepartment().getId()).get();
            List<EducationDetails> educationDetailsList = educationalRepository.findAllByStudentId(student.getID());
            List<EducationQualificationPayload> educationQualificationPayloads = new ArrayList<>();

            StudentPayload studentPayload = new StudentPayload();
            studentPayload.setAddress(student.getAddress());
            studentPayload.setGender(student.getGender().toString());
            studentPayload.setCitizenshipNumber(student.getCitizenshipNumber());
            studentPayload.setEnrolledYear(course.getEnrolledYear());
            studentPayload.setEmailAddress(student.getEmailAddress());
            studentPayload.setDateOfBirth(student.getDateOfBirth());
            studentPayload.setDepartmentName(department.getName());
            studentPayload.setCourseName(course.getName());
            studentPayload.setMaritalStatus(student.getMaritalStatus());
            studentPayload.setMobileNumber(student.getMobileNumber());
            studentPayload.setMoodifiedDate(student.getModifiedDate().toString().substring(0, 10));
            studentPayload.setStatus(student.getStatus());

            for (EducationDetails educationDetails : educationDetailsList) {
                EducationQualificationPayload educationQualificationPayload = new EducationQualificationPayload();
                educationQualificationPayload.setCourse(educationDetails.getCourse());
                educationQualificationPayload.setLevel(educationDetails.getLevel());
                educationQualificationPayload.setInstituteName(educationDetails.getInstituteName());
                educationQualificationPayload.setUniversityName(educationDetails.getUniversityName());
                educationQualificationPayloads.add(educationQualificationPayload);
            }
            studentPayload.setEducationalQualification(educationQualificationPayloads);
            studentPayload.setFirstName(student.getFirstName());
            studentPayload.setMiddleName(student.getMiddleName());
            studentPayload.setLastName(student.getLastName());
            return ResponseUtility.resourceFound(studentPayload, "Student found", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceNotFound(null, "Student not found", HttpStatus.OK);
        }

    }

    @Override
    public ResponseObject updateStudent(RegistrationRequest student) {
        try {
            Student newStudent = studentRepository.findByContact(student.getMobileNumber());
            newStudent.setAddress(student.getAddress());
            newStudent.setGender(student.getGender());
            newStudent.setCitizenshipNumber(student.getCitizenshipNumber());
            newStudent.setDateOfBirth(student.getDateOfBirth());
            newStudent.setFirstName(student.getFirstName());
            newStudent.setMaritalStatus(student.getMaritalStatus());
            newStudent.setLastName(student.getLastName());
            newStudent.setMiddleName(student.getMiddleName());
            newStudent.setMobileNumber(student.getMobileNumber());
            newStudent.setEmailAddress(student.getEmailAddress());
            newStudent.setAddedBy(JwtAuthenticationFilter.name);
            newStudent.setModifiedBy(JwtAuthenticationFilter.name);
            newStudent.setModifiedDate(new Date());
            newStudent.setStatus("ACTIVE");
            List<Course> availableCourses = courseRepository.findByName(student.getCourseName());
            if (availableCourses == null) {
                return ResponseUtility.resourceNotFound(null, "No available course found", HttpStatus.OK);
            }
            for (Course course : availableCourses) {
                if (course.getEnrolledYear().equals(student.getEnrolledYear())) {
                    System.out.println(course);
                    newStudent.setCourse(course);
                }
            }
            studentRepository.save(newStudent);
            List<EducationDetails> educational_details = educationalRepository.findAllByStudentId(newStudent.getID());
            for (EducationDetails educationDetails : educational_details) {
                educationalRepository.delete(educationDetails);
            }
            List<EducationDetails> educationDetailsList = student.getEducational_details();
            for (EducationDetails educationDetails : educationDetailsList) {
                educationDetails.setStudent(newStudent);
                educationalRepository.save(educationDetails);
            }
            return ResponseUtility.resourceCreated(student, "student updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceNotCreated(student, "Student couldnt be updated", HttpStatus.OK);
        }
    }

    @Override
    public ResponseObject deleteStudent(String number) {
        try {
            Student student = studentRepository.findByContact(number);
            System.out.println(student.getStatus() + "checking--------------");
            student.setStatus("DELETED");
            student.setModifiedBy(JwtAuthenticationFilter.name);
            student.setModifiedDate(new Date());
            studentRepository.save(student);
            return ResponseUtility.resourceCreated(null, student.getFirstName() + " updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceNotCreated(null, "Student couldnt be updated", HttpStatus.OK);
        }
    }

    @Override
    public ResponseObject changeStudent(String contact) {
        try {
            Student student = studentRepository.findByContact(contact);
            String status = student.getStatus();
            System.out.println(status);
            if (status.equalsIgnoreCase("ACTIVE")) {
                student.setStatus("INACTIVE");
                student.setModifiedBy(JwtAuthenticationFilter.name);
                student.setModifiedDate(new Date());
                studentRepository.save(student);
                return ResponseUtility.resourceCreated(null, student.getFirstName() + "'s status changed", HttpStatus.OK);
            } else if (status.equalsIgnoreCase("INACTIVE")) {
                student.setStatus("ACTIVE");
                student.setModifiedDate(new Date());
                studentRepository.save(student);
                return ResponseUtility.resourceCreated(null, student.getFirstName() + "'s status changed", HttpStatus.OK);
            } else
                return ResponseUtility.resourceNotCreated(null, student.getFirstName() + " is disabled", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceCreated(null, "Student's status couldnt be changed ", HttpStatus.OK);
        }
    }
}
