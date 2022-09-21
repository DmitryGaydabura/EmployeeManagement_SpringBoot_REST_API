package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeConverter {

    private final MapperFacade mapperFacade;

    public EmployeeConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public EmployeeDto toDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeDto.class);
    }

    public EmployeeReadDto toReadDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeReadDto.class);
    }


    public Employee fromDto(EmployeeDto dto) {
        return mapperFacade.map(dto, Employee.class);
    }

    public EmployeeUpdateDto toUpdateDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeUpdateDto.class);
    }

    public EmployeeReadAllByNameDto toReadAllByNameDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeReadAllByNameDto.class);
    }

    public EmployeeReadAllByIsFullDto toReadAllByIsFullDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeReadAllByIsFullDto.class);
    }


}
