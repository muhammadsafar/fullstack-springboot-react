package com.msbahrddin.upscaletest.service.impl;

import com.msbahrddin.upscaletest.dto.EmployeeDTO;
import com.msbahrddin.upscaletest.entity.Department;
import com.msbahrddin.upscaletest.entity.Employee;
import com.msbahrddin.upscaletest.exception.EmailAlreadyExistException;
import com.msbahrddin.upscaletest.exception.ResourceNotFoundException;
import com.msbahrddin.upscaletest.repository.DepartmentRepository;
import com.msbahrddin.upscaletest.repository.EmployeeRepository;
import com.msbahrddin.upscaletest.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;


    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        LOGGER.info(String.format("employee create run @service -> employee "));

        Optional<Employee> emp = employeeRepository.findEmployeeByEmail(employeeDTO.getEmail());
        if (emp.isPresent()) {
            throw new EmailAlreadyExistException("Employee", employeeDTO.getEmail());
        }
        Optional<Department> checkDept = departmentRepository.findById(employeeDTO.getDepartment().getDepartmentId());
        if (checkDept.isEmpty()) {
            throw new ResourceNotFoundException("Department", "id", employeeDTO.getDepartment().getDepartmentId().toString());
        }

        Employee empl = modelMapper.map(employeeDTO, Employee.class);
        empl.setDepartment(checkDept.get());
        Employee emlpSave = employeeRepository.save(empl);

        return modelMapper.map(emlpSave, EmployeeDTO.class);

    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee empl = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id.toString()));
        return modelMapper.map(empl, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByNameOrEmail(String keyword) {
        List<Employee> empl = employeeRepository.findEmployeeByNameOrEmail(keyword).orElseThrow(() -> new ResourceNotFoundException("Employee", "name or email", keyword));
        return empl.stream().map((emp -> modelMapper.map(emp, EmployeeDTO.class))).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> datas = employeeRepository.findAll();
        return datas.stream().map((data) -> modelMapper.map(data, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employee, Long id) {
        Employee get = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id.toString()));

        Employee update = get;

        update.setFirstName(employee.getFirstName());
        update.setLastName(employee.getLastName());
        update.setEmail(employee.getEmail());

        Optional<Department> checkDept = departmentRepository.findById(employee.getDepartment().getDepartmentId());
        if (checkDept.isEmpty()) {
            throw new ResourceNotFoundException("Department", "id", employee.getDepartment().getDepartmentId().toString());
        }

        update.setDepartment(checkDept.get());
        Employee updated = employeeRepository.save(update);

        return modelMapper.map(updated, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee get = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id.toString()));
        employeeRepository.deleteById(get.getId());
    }
}
