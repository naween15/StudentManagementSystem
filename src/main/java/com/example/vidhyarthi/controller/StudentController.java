package com.example.vidhyarthi.controller;

import com.example.vidhyarthi.Request.RegistrationRequest;
import com.example.vidhyarthi.Response.ResponseObject;
import com.example.vidhyarthi.repository.StudentRepository;
import com.example.vidhyarthi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    private StudentRepository studentRepository;

    @PostMapping("/save")
    public ResponseEntity<Object> saveStudent(@Valid @RequestBody RegistrationRequest student) {
        student.setDateOfBirth(student.getDateOfBirth().substring(0, 10));
        ResponseObject responseObject = studentService.saveStudent(student);
        return ResponseEntity.status(responseObject.getStatus()).body(responseObject.getBody());
    }

    @GetMapping("/get/{name}")
    public ResponseObject getStudent(@PathVariable("name") String name) {
        return studentService.getStudentDetailsByName(name);

    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getAllStudent() {
        ResponseObject responseObject = studentService.getStudentDetails();
        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
//        return new ResponseEntity<>(studentService.getStudentDetails(), HttpStatus.OK);
    }

    @GetMapping("/contact/{number}")
    public ResponseEntity getStudentByContact(@PathVariable("number") String contact) {
        return new ResponseEntity<>(studentService.getStudentByContact(contact), HttpStatus.OK);
    }

    @GetMapping("/delete/{number}")
    public ResponseEntity<String> deleteStudent(@PathVariable("number") String number) {
        studentService.deleteStudent(number);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody RegistrationRequest registrationRequest) {
        registrationRequest.setDateOfBirth(registrationRequest.getDateOfBirth().substring(0, 10));
        studentService.updateStudent(registrationRequest);
        return ResponseEntity.ok().body("done");
    }

    @GetMapping("/change/{contact}")
    public ResponseEntity<String> changeStatus(@PathVariable String contact) {
        studentService.changeStudent(contact);
        return ResponseEntity.ok().body("done");
    }
}
