package com.msbahrddin.upscaletest.repository;

import com.msbahrddin.upscaletest.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findDepartmentByDepartmentCode(String deptCode);
}
