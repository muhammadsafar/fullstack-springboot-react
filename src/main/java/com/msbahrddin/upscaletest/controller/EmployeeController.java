package com.msbahrddin.upscaletest.controller;

import com.msbahrddin.upscaletest.dto.EmployeeDTO;
import com.msbahrddin.upscaletest.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Resource", description = "Rest APIs for Employee Services")
@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;


    @Operation(
            summary = "Create Employee",
            description = "Service to save employee into DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO save = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Employee",
            description = "Service to fetch employee by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO get = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(get);
    }

    @Operation(
            summary = "Find Employee",
            description = "Service to fetch employee by name or email, possible data in list"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/find")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByNameOrEmail(@RequestParam String keyword) {
        List<EmployeeDTO> get = employeeService.getEmployeeByNameOrEmail(keyword);
        return ResponseEntity.ok(get);
    }

    @Operation(
            summary = "Find Employee",
            description = "Service to fetch all employee"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @Operation(
            summary = "Update Employee",
            description = "Service to edit employee"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        EmployeeDTO get = employeeService.updateEmployee(employeeDTO, id);
        return ResponseEntity.ok(get);
    }

    @Operation(
            summary = "Delete Employee",
            description = "Service to delete employee by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(String.format("Employee with id-%s deleted successfully", id));
    }

}
