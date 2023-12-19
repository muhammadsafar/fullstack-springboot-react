package com.msbahrddin.upscaletest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
@Builder
@ToString
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String departmentCode;
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
