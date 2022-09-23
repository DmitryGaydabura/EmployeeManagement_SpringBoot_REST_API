package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.service.ServiceBean;
import com.example.demowithtests.util.config.mapstruct.EmployeeToDtoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    private final EmployeeToDtoMapper mapper
            = Mappers.getMapper(EmployeeToDtoMapper.class);
    @Mock
    private Repository repository;
    @InjectMocks
    private ServiceBean service;


//    @Test
//    public void whenSaveEmployee_shouldReturnEmployee() {
//        Employee employee = new Employee();
//        employee.setName("Mark");
//
//        when(service.create(ArgumentMatchers.any(Employee.class))).thenReturn(mapper.employeeToCreateDto(employee));
//
//        EmployeeCreateDto created = service.create(employee);
//
//        assertThat(created.getName()).isSameAs(employee.getName());
//    }

//    @Test
//    public void whenGivenId_shouldReturnEmployee_ifFound() {
//        EmployeeReadDto employee = new EmployeeReadDto();
//        employee.setId(88);
//
//        when(repository.findById(employee.getId())).thenReturn(employee);
//
//        EmployeeReadDto expected = service.getById(employee.getId());
//
//        assertThat(expected).isSameAs(employee);
//        verify(repository).findById(employee.getId());
//    }

    @Test(expected = EntityNotFoundException.class)
    public void should_throw_exception_when_employee_doesnt_exist() {
        Employee employee = new Employee();
        employee.setId(89);
        employee.setName("Mark");

        given(repository.findById(anyInt())).willReturn(Optional.empty());
        service.getById(employee.getId());
    }

    @Test
    public void when_name_given_should_return_all_employees_by_name() {

        Employee created = new Employee();
        created.setName("Mark");

        List<Employee> employee = new ArrayList<>();
        employee.add(created);

        when(repository.getAllByName("Mark")).thenReturn(employee);

        List<Employee> expected = service.getListAllByName("Mark");

        assertThat(expected.size()).isGreaterThan(0);
        assertEquals("Mark", expected.get(0).getName());
        assertThat(expected).isSameAs(employee);

        verify(repository).getAllByName("Mark");

    }

    @Test
    public void when_called_should_returnAllEmployees_ByIsFull() {

        Employee created = new Employee();
        created.setIsFull(true);

        List<Employee> employee = new ArrayList<>();
        employee.add(created);

        when(repository.getAllByIsFullTrue()).thenReturn(employee);

        List<Employee> expected = service.getAllByIsFullTrue();

        assertThat(expected.size()).isGreaterThan(0);
        assertEquals(true, expected.get(0).getIsFull());
        assertThat(expected).isSameAs(employee);

        verify(repository).getAllByIsFullTrue();

    }

//    @Test
//    public void when_idGiven_should_returnEmployeeWithPassword() {
//        int id = 1;
//        Employee created = new Employee();
//        created.setId(id);
//        created.setPassword("123");
//
//        when(repository.findById(id)).thenReturn(Optional.of(created));
//
//        Employee employee = service.generatePassword(id);
//
//        assertThat(employee.getPassword()).isEqualTo(created.getPassword());
//
//
//    }

//    @Test
//    public void when_id_given_should_return_employee_with_password() {
//        int id = 1;
//        Employee created = new Employee();
//        created.setId(id);
//        created.setPassword("123");
//
//        when(repository.findById(id)).thenReturn(Optional.of(created));
//
//        Employee employee = service.generatePassword(id);
//
//        assertThat(employee.getPassword()).isEqualTo(created.getPassword());
//
//    }

//    @Test
//    public void when_EmployeeGiven_should_return_employeeWithUpdatedPassword() {
//        int id = 1;
//        Employee created = new Employee();
//        created.setId(id);
//        created.setPassword("1234");
//
//        Employee newEmployee = new Employee();
//        newEmployee.setId(1);
//        newEmployee.setPassword("0000");
//
//        when(repository.findById(id)).thenReturn(Optional.of(newEmployee));
//
//        Employee employee = service.updatePasswordById(newEmployee);
//
//        assertThat(employee.getPassword()).isNotEqualTo(created.getPassword());
//
//
//    }


}
