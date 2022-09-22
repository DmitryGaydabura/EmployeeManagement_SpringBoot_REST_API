package com.example.demowithtests.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateDto {
    private Integer id;
    private String name;
    private String country;
    private String email;
    private Boolean isFull;
    private Boolean isUpdated;
}
