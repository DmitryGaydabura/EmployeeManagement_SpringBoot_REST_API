package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeToDtoMapper {

    EmployeeReadDto employeeToReadDto(Employee employee);
    Employee employeeReadDtoToEmployee(EmployeeReadDto employeeReadDto);

    EmployeeCreateDto employeeToCreateDto(Employee employee);
    Employee employeeCreateDtoToEmployee(EmployeeCreateDto employeeCreateDto);

    EmployeeUpdateDto employeeToUpdateDto(Employee employee);
    Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);

}
