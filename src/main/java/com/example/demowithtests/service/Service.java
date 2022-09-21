package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeReadAllByIsFullDto;
import com.example.demowithtests.dto.EmployeeReadAllByNameDto;

import java.util.List;

public interface Service {

    /**
     * Create a new employee.
     *
     * @param employee The employee object to be created.
     * @return The employee object that was created.
     */
    Employee create(Employee employee);

    /**
     * Get all employees.
     *
     * @return A list of all the employees in the database.
     */
    List<Employee> getAll();

    /**
     * Get an employee by id.
     *
     * @param id The id of the employee to be retrieved.
     * @return Employee
     */
    Employee getById(Integer id);

    /**
     * Update an employee by id.
     *
     * @param id The id of the employee you want to update.
     * @param plane The object that will be updated in the database.
     * @return Employee
     */
    Employee updateById(Integer id, Employee plane);

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
    List<Employee> getListAllByName(String name);

    /**
     * Get all employees where isFull is true.
     *
     * @return A list of all employees who are full time.
     */
    List<Employee> getAllByIsFullTrue();

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

    /**
     * Convert a list of Employee objects to a list of EmployeeReadAllByNameDto objects.
     *
     * @param list The list of Employee objects to be converted to EmployeeReadAllByNameDto objects.
     * @return List<EmployeeReadAllByNameDto>
     */
    List<EmployeeReadAllByNameDto> employeeListToReadAllByNameDto(List<Employee> list);

    /**
     * Convert a list of Employee objects to a list of EmployeeReadAllByIsFullDto objects
     *
     * @param list The list of Employee objects to be converted.
     * @return List<EmployeeReadAllByIsFullDto>
     */
    List<EmployeeReadAllByIsFullDto> employeeListToReadAllByIsFullDto(List<Employee> list);

}
