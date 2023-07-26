package com.example.vidhyarthi.serviceImpl;

import com.example.vidhyarthi.Request.DepartmentRequest;
import com.example.vidhyarthi.entity.Department;
import com.example.vidhyarthi.repository.DepartmentRepository;
import com.example.vidhyarthi.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public String saveDepartment(DepartmentRequest departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCourse(departmentDto.getCourse());
        department.setHod(departmentDto.getHod());
        department.setContactNo(departmentDto.getContactNo());
        departmentRepository.save(department);
        return "done";
    }
}
