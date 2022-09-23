package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import com.example.demowithtests.service.Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class Controller implements ControllerInterface {

    private final Service service;

    @Override
    public EmployeeCreateDto saveEmployee(@RequestBody @Valid Employee requestForSave) {
        return service.create(requestForSave);
    }

    @Override
    public List<Employee> getAllUsers() {
        return service.getAllEmployees();
    }

    @Override
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @Override
    public EmployeeUpdateDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return service.updateById(id, employee);
    }

    @Override
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    @Override
    public void removeAllUsers() {
        service.removeAll();
    }

    @Override
    public List<Employee> getAllByName(@RequestParam(value = "name") String name) {
        return service.getListAllByName(name);
    }

    @Override
    public List<Employee> getAllByIsFullTrue() {
        return service.getAllByIsFullTrue();
    }

    @Override
    public Employee generateRandomPassword(@RequestParam(value = "id") int id) {
        return service.generatePassword(id);
    }

    @Override
    public Employee updatePassword(@RequestBody Employee employee) {
        return service.updatePasswordById(employee);
    }
}
