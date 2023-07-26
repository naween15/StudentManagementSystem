package com.example.vidhyarthi.service;

import com.example.vidhyarthi.Request.RegistrationRequest;
import com.example.vidhyarthi.Response.ResponseObject;

public interface StudentService {
    public ResponseObject saveStudent(RegistrationRequest studentDto);

    public ResponseObject getStudentDetails();


    public ResponseObject getStudentDetailsByName(String name);

    public ResponseObject getStudentByContact(String contact);

    public ResponseObject updateStudent(RegistrationRequest studentDTO);

    public ResponseObject deleteStudent(String number);

    public ResponseObject changeStudent(String contact);
}
