package com.example.vidhyarthi.controller;

import com.example.vidhyarthi.entity.EducationDetails;
import com.example.vidhyarthi.repository.EducationalRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/education")

public class EducationController {
    private EducationalRepository educationalRepository;

    @PostMapping("/save")
    public String saveEducation(@RequestBody EducationDetails educational_details) {
        educationalRepository.save(educational_details);
        return "done";
    }

    @GetMapping("/get")
    public List<EducationDetails> getDepartment() {
        return educationalRepository.findAll();
    }

}
