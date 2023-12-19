package com.msbahrddin.upscaletest.controller;

import com.msbahrddin.upscaletest.dto.DepartmentDTO;
import com.msbahrddin.upscaletest.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Department Resource", description = "Rest APIs for Department Services")
@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {


    private DepartmentService departmentService;


    @Operation(
            summary = "Create Department",
            description = "Service to save department into DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {

        DepartmentDTO save = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get Department by id",
            description = "Service to fetch department by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO dept = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(dept);
    }

    @Operation(
            summary = "Get Department by code",
            description = "Service to fetch department by code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/find")
    public ResponseEntity<DepartmentDTO> getDepartmentByCode(@RequestParam String code) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentByCode(code);
        return ResponseEntity.ok(departmentDTO);
    }

    @Operation(
            summary = "Get All Departments",
            description = "Service to fetch all departments"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
        List<DepartmentDTO> datas = departmentService.getAllDepartment();
        return ResponseEntity.ok(datas);
    }

    @Operation(
            summary = "Update Department by id",
            description = "Service to edit department by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable Long id) {
        DepartmentDTO update = departmentService.updateDepartment(departmentDTO, id);
        return ResponseEntity.ok(update);
    }

    @Operation(
            summary = "Delete Department by id",
            description = "Service to remove department by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long id) {
        departmentService.deletedDepartment(id);
        return ResponseEntity.ok(String.format("Department with id-%s deleted successfully", id));
    }

}
