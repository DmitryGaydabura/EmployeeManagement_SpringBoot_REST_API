package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    /**
     * Get all employees by name.
     *
     * @param name The name of the method.
     * @return A list of employees with the name specified.
     */
    //@Query(value = "Select * From users Where name = ?1", nativeQuery = true)
    Page<Employee> findByName(String name,Pageable pageable);

    // The query below is redundant, because Spring Data "getAllByIsFullTrue"
    // is doing the same effect
    /**
     * This function returns a list of all employees who are fully described in DB
     */
    @Query(value = "Select * From users Where is_full = true", nativeQuery = true)
    Page<Employee> getAllByIsFullTrue(Pageable pageable);
    @Query(value = "Select * From users", nativeQuery = true)
    Page<Employee> getAll(Pageable pageable);

    List<Employee> findAll();
}
