package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.PasswordGenerator;
import com.example.demowithtests.util.ResourceNotFoundException;
import com.example.demowithtests.util.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        employee.setIsFull(employee.getCountry() != null && employee.getName() != null && employee.getEmail() != null);
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setIsUpdated(true);
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
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

    /**
     * Get all employees with the given name.
     *
     * @param name The name of the method.
     * @return A list of employees with the name specified.
     */
    public List<Employee> getAllByName(String name) {
        return repository.getAllByName(name);
    }

    /**
     * Get all employees where isFull is true.
     *
     * @return A list of all employees who are full time.
     */
    public List<Employee> getAllByIsFullTrue() {
        return repository.getAllByIsFullTrue();
    }

    /**
     * > Generate a random password for the employee with the given id and save it to the database
     *
     * @param id The id of the employee whose password is to be generated.
     * @return Employee
     */
    @Override
    public Employee generatePassword(int id) {
        Employee employee = repository.findById(id).orElseThrow();
        String p = PasswordGenerator.generate();
        employee.setPassword(p);
        repository.save(employee);
        return employee;
    }

    /**
     * It updates the password of an employee by id.
     *
     * @param employee The employee object that is passed in from the front end.
     * @return The updated employee.
     */
    @Override
    public Employee updatePasswordById(Employee employee) {
        Integer id = employee.getId();
        String password = employee.getPassword();
        Employee updatedEmployee = repository.findById(id).orElseThrow();
        updatedEmployee.setPassword(password);
        repository.save(updatedEmployee);
        return updatedEmployee;
    }
}
