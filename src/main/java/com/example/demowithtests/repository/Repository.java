package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    /**
     * Find an employee by name.
     *
     * @param name The name of the method.
     * @return Employee
     */
    Employee findByName(String name);

    // query below is doing the same effect
    // @Query(value = "Select * From users Where name = ?1", nativeQuery = true)

    /**
     * Get all employees by name.
     *
     * @param name The name of the method.
     * @return A list of employees with the name specified.
     */
    List<Employee> getAllByName(String name);

    // The query below is redundant, because Spring Data "getAllByIsFullTrue"
    // is doing the same effect
    /**
     * This function returns a list of all employees who are fully described in DB
     */
    @Query(value = "Select * From users Where is_full = true", nativeQuery = true)
    List<Employee> getAllByIsFullTrue();
    @Query(value = "Select * From users", nativeQuery = true)
    List<Employee> getAll();
}
