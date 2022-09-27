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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository employeeRepository;
    private EmployeeToDtoMapper mapper;


    @Override
    public EmployeeCreateDto create(Employee employee) {
        employee.setIsFull(employee.getCountry() != null && employee.getName() != null && employee.getEmail() != null);

        return mapper.employeeToCreateDto(employeeRepository.save(employee));
    }


    @Override
    public Page<Employee> getAllWithPagination(Pageable pageable) {
        log.debug("getAllWithPagination() - start: pageable = {}", pageable);
        Page<Employee> list = employeeRepository.getAll(pageable);
        log.debug("getAllWithPagination() - end: list = {}", list);
        return list;
    }


    @Override
    public EmployeeReadDto getById(Integer id) {
        return mapper.employeeToReadDto(employeeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }


    @Override
    public EmployeeUpdateDto updateById(Integer id, Employee employee) {
        return mapper.employeeToUpdateDto(employeeRepository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setIsUpdated(true);
                    return employeeRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id)));
    }


    @Override
    public void removeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(ResourceWasDeletedException::new);
        employeeRepository.delete(employee);
    }


    @Override
    public void removeAll() {
        employeeRepository.deleteAll();

    }


    public Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return employeeRepository.findByName(name, pageable);
    }


    public Page<Employee> getAllByIsFullTrue(Pageable paging) {
        return employeeRepository.getAllByIsFullTrue(paging);
    }


    @Override
    public Employee generatePassword(int id) {
        Employee employee = employeeRepository.getById(id);
        String p = PasswordGenerator.generate();
        employee.setPassword(p);
        employeeRepository.save(employee);
        return employee;
    }


    @Override
    public Employee updatePasswordById(Employee employee) {
        Integer id = employee.getId();
        String password = employee.getPassword();
        Employee updatedEmployee = employeeRepository.getById(id);
        updatedEmployee.setPassword(password);
        employeeRepository.save(updatedEmployee);
        return updatedEmployee;
    }


    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }


}
