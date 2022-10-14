package com.example.demowithtests.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto {

    private Integer serialNumber;

    private Date dateOfExpiry;
}
