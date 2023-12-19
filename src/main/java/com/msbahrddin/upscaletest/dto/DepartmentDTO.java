package com.msbahrddin.upscaletest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msbahrddin.upscaletest.entity.Employee;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {

    private Long departmentId;

    @NotEmpty(message = "department code should not be null or empty")
    @Size(min = 2, max = 5)
    private String departmentCode;

    @NotEmpty(message = "department name should not be null or empty")
    private String departmentName;

    @JsonIgnore
    private List<Employee> employees;

}
