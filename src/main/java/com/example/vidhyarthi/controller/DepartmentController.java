package com.example.vidhyarthi.controller;

import com.example.vidhyarthi.Request.DepartmentRequest;
import com.example.vidhyarthi.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/department")

public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping("/add")
    public void AddDepartment(@Valid @RequestBody DepartmentRequest department) {
        departmentService.saveDepartment(department);
    }
}
