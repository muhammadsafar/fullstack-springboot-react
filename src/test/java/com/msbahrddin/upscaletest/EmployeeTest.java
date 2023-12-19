package com.msbahrddin.upscaletest;

import com.msbahrddin.upscaletest.entity.Department;
import com.msbahrddin.upscaletest.entity.Employee;
import com.msbahrddin.upscaletest.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest() {
        Employee employee = Employee.builder()
                .firstName("Abdullah")
                .lastName("Syakir")
                .email("muhammad.ict1487@gmail.com")
                .build();
        employeeRepository.save(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTestById() {
        Employee employee = employeeRepository.findById(1L).get();
        Assertions.assertThat(employee.getId()).isEqualTo(1L);

    }


    @Test
    @Order(3)
    public void getListOfEmployeesTest() {
        List<Employee> employees = employeeRepository.findAll();
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void getEmployeeByEmailOrName() {
        List<Employee> eem = employeeRepository.findEmployeeByNameOrEmail("ahmad").get();
        Assertions.assertThat(eem.stream().filter(fx -> fx.getFirstName().contains("ahmad")).collect(Collectors.toList())).isNotNull();
    }

    @Test
    @Order(6)
    @Rollback(value = true)
    public void updateEmployeeTest() {

        Employee employee = employeeRepository.findById(1L).get();

        employee.setEmail("musa@gmail.com");

        Employee employeeUpdated = employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("musa@gmail.com");

    }


    @Test
    @Order(7)
    @Rollback(value = false)
    public void deleteEmployeeTest() {

        Employee employee = employeeRepository.findEmployeeByEmail("muhammad.ict1487@gmail.com").get();

        employeeRepository.delete(employee);


        Employee checkAgain = null;

        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail("muhammad.ict1487@gmail.com");

        if (optionalEmployee.isPresent()) {
            checkAgain = optionalEmployee.get();
        }

        Assertions.assertThat(checkAgain).isNull(); //expected is null
    }


}
