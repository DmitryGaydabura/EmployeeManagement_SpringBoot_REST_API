package com.example.demowithtests.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateDto {
    private Integer id;
    private String name;
}
