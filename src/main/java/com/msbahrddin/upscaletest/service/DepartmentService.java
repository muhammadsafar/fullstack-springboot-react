package com.msbahrddin.upscaletest.service;

import com.msbahrddin.upscaletest.dto.DepartmentDTO;
import com.msbahrddin.upscaletest.entity.Department;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO department);

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO getDepartmentByCode(String code);

    List<DepartmentDTO> getAllDepartment();

    DepartmentDTO updateDepartment(DepartmentDTO department, Long id);

    void deletedDepartment(Long id);
}
