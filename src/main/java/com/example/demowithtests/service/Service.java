package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service {

    /**
     * Create a new employee.
     *
     * @param employee The employee object to be created.
     * @return The employee object that was created.
     */
    EmployeeCreateDto create(Employee employee);


    Page<Employee> getAllWithPagination(Pageable pageable);

    /**
     * Get an employee by id.
     *
     * @param id The id of the employee to be retrieved.
     * @return Employee
     */
    EmployeeReadDto getById(Integer id);

    /**
     * Update an employee by id.
     *
     * @param id    The id of the employee you want to update.
     * @param plane The object that will be updated in the database.
     * @return Employee
     */
    EmployeeUpdateDto updateById(Integer id, Employee plane);

    /**
     * Removes the entity with the given id.
     *
     * @param id The id of the object to be removed.
     */
    void removeById(Integer id);

    /**
     * Removes all of the elements from this list
     */
    void removeAll();

    /**
     * Get a list of all employees with the given name.
     *
     * @param name The name of the method.
     * @return A list of all employees with the given name.
     */
    Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder);

    /**
     * Get all employees where isFull is true.
     *
     * @return A list of all employees who are full time.
     */
    Page<Employee> getAllByIsFullTrue(Pageable pageable);

    /**
     * Generate a password for the employee with the given id.
     *
     * @param id The id of the employee whose password is to be generated.
     * @return Employee
     */
    Employee generatePassword(int id);

    /**
     * Update the password of the employee with the given id.
     *
     * @param employee The employee object that contains the new password.
     * @return Employee
     */
    Employee updatePasswordById(Employee employee);


}
