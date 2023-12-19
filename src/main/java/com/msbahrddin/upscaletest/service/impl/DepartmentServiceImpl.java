package com.msbahrddin.upscaletest.service.impl;

import com.msbahrddin.upscaletest.controller.DepartmentController;
import com.msbahrddin.upscaletest.dto.DepartmentDTO;
import com.msbahrddin.upscaletest.entity.Department;
import com.msbahrddin.upscaletest.exception.DepartmentCodeAlreadyExistException;
import com.msbahrddin.upscaletest.exception.ResourceNotFoundException;
import com.msbahrddin.upscaletest.repository.DepartmentRepository;
import com.msbahrddin.upscaletest.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDto) {
        LOGGER.info(String.format("save dept run @service -> for dept code : %s", departmentDto.getDepartmentCode()));

        Optional<Department> check = departmentRepository.findDepartmentByDepartmentCode(departmentDto.getDepartmentCode());
        if (check.isPresent()) {
            throw new DepartmentCodeAlreadyExistException("Department", departmentDto.getDepartmentCode());
        }

        Department department = modelMapper.map(departmentDto, Department.class);
        Department save = departmentRepository.save(department);
        return modelMapper.map(save, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        LOGGER.info(String.format("get dept by id run @service -> for id : %s", id));
        Department opt = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department", "id", id.toString()));
        return modelMapper.map(opt, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String code) {
        LOGGER.info(String.format("get department by code run @service -> for code : %s", code));
        Department get = departmentRepository.findDepartmentByDepartmentCode(code).orElseThrow(() -> new ResourceNotFoundException("Department", "code", code));
        return modelMapper.map(get, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        LOGGER.info(String.format("get all dept run @service..."));
        List<Department> depts = departmentRepository.findAll();
        return depts.stream().map((dept) -> modelMapper.map(dept, DepartmentDTO.class)).collect(Collectors.toList());

    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long id) {
        LOGGER.info(String.format("update dept by id run @service -> for id : %s", id));
        Department get = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department", "id", id.toString()));

        get.setDepartmentCode(departmentDTO.getDepartmentCode());
        get.setDepartmentName(departmentDTO.getDepartmentName());
        Department save = departmentRepository.save(get);
        return modelMapper.map(save, DepartmentDTO.class);
    }

    @Override
    public void deletedDepartment(Long id) {
        Department opt = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department", "id", id.toString()));
        departmentRepository.deleteById(opt.getDepartmentId());
    }
}
