package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.config.mapstruct.EmployeeToDtoMapper;
import com.example.demowithtests.util.exceptions.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;
    private EmployeeToDtoMapper mapper
            = Mappers.getMapper(EmployeeToDtoMapper.class);


    @Override
    public EmployeeCreateDto create(Employee employee) {
        employee.setIsFull(employee.getCountry() != null && employee.getName() != null && employee.getEmail() != null);

        return mapper.employeeToCreateDto(repository.save(employee));
    }


    @Override
    public List<Employee> getAllEmployees() {
        return repository.getAll();
    }


    @Override
    public EmployeeReadDto getById(Integer id) {
        return mapper.employeeToReadDto(repository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }


    @Override
    public EmployeeUpdateDto updateById(Integer id, Employee employee) {
        return mapper.employeeToUpdateDto(repository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setIsUpdated(true);
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id)));
    }


    @Override
    public void removeById(Integer id) {
        Employee employee = repository.findById(id)
                .orElseThrow(ResourceWasDeletedException::new);
        repository.delete(employee);
    }


    @Override
    public void removeAll() {
        repository.deleteAll();

    }


    public List<Employee> getListAllByName(String name) {
        return repository.getAllByName(name);
    }


    public List<Employee> getAllByIsFullTrue() {
        return repository.getAllByIsFullTrue();
    }


    @Override
    public Employee generatePassword(int id) {
        Employee employee = repository.getById(id);
        String p = PasswordGenerator.generate();
        employee.setPassword(p);
        repository.save(employee);
        return employee;
    }


    @Override
    public Employee updatePasswordById(Employee employee) {
        Integer id = employee.getId();
        String password = employee.getPassword();
        Employee updatedEmployee = repository.getById(id);
        updatedEmployee.setPassword(password);
        repository.save(updatedEmployee);
        return updatedEmployee;
    }


}
