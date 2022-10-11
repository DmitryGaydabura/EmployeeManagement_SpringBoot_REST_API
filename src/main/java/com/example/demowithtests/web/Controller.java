package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import com.example.demowithtests.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public Page<Employee> getAllPageable(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return service.getAllWithPagination(paging);
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
    public Page<Employee> findByName(@RequestParam(value = "name") String name,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "") List<String> sortList,
                                     @RequestParam(defaultValue = "DESC") String sortOrder
    ) {
        return service.findByName(name, page, size, sortList, sortOrder);
    }

    @Override
    public Page<Employee> getAllByIsFullTrue(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return service.getAllByIsFullTrue(paging);
    }

    @Override
    public Employee generateRandomPassword(@RequestParam(value = "id") int id) {
        return service.generatePassword(id);
    }

    @Override
    public Employee updatePassword(@RequestBody Employee employee) {
        return service.updatePasswordById(employee);
    }

    @Override
    public List<Employee> getAllByNameStream(@RequestParam(value = "name") String name){
        return service.getAllByNameStream(name);
    }

    @Override
    public List<Employee> getAllByIsFullStream(){
        return service.getAllByIsFullStream();
    }

    @Override
    public List<Employee> getAllEmployeesWithPassword(@RequestParam(value = "password") String password){
        return service.getEmployeesWithPassword(password);
    }

    @Override
    public List<Employee> getAllEmployeesWithEmail(@RequestParam(value = "email") String email){
        return service.getEmployeesWithEmail(email);
    }


}
