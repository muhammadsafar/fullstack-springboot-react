package com.msbahrddin.upscaletest.service;

import com.msbahrddin.upscaletest.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employee);

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getEmployeeByNameOrEmail(String keyword);

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO updateEmployee(EmployeeDTO employee, Long id);

    void deleteEmployee(Long id);

}
