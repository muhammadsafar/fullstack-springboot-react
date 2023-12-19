package com.msbahrddin.upscaletest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeDTO {

    private Long id;

    @NotEmpty(message = "first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "last name should not be null or empty")
    private String lastName;

    @Email(message = "field must be valid with email format")
    @NotEmpty(message = "email should not be null or empty")
    private String email;

    private DepartmentDTO department;

}
