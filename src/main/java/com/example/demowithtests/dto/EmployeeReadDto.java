package com.example.demowithtests.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReadDto {

    private Integer id;
    private String name;
    private String country;
    private String email;

}
