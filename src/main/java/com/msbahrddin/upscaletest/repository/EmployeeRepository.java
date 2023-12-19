package com.msbahrddin.upscaletest.repository;

import com.msbahrddin.upscaletest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findEmployeeByEmail(String email);

    @Query(value = " SELECT * FROM employees em where em.first_name like %:keyword% or em.last_name like %:keyword% or em.email like %:keyword% ", nativeQuery = true)
    public Optional<List<Employee>> findEmployeeByNameOrEmail(@Param("keyword") String key);
}
