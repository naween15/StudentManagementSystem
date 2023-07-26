package com.example.vidhyarthi.service;

import com.example.vidhyarthi.dto.Studentdetails;

import java.util.List;

public interface SearchFilterService {
    public List<Studentdetails> getFilteredStudents(String filter);


}
