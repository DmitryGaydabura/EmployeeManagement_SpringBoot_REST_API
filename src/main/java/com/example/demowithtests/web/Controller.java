package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import com.example.demowithtests.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class Controller {

    private final Service service;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeCreateDto saveEmployee(@RequestBody @Valid Employee requestForSave) {
        return service.create(requestForSave);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @Operation(summary = "This is endpoint to get all employees.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description = "Create request to get all employees", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {

        return service.getById(id);
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User was updated!"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @Operation(summary = "This is endpoint to update employee by ID.", description = "Update (Name,Country,Email only!) by ID", tags = {"Employee"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmployeeUpdateDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return service.updateById(id,employee);
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @Operation(summary = "This is endpoint to remove employee by ID.", description = "Create request to remove employee by ID.", tags = {"Employee"})

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @Operation(summary = "This is endpoint to remove all employees.", description = "Create request to remove all employees.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }

    /**
     * This function returns a list of employees whose name matches the name parameter
     *
     * @param name The name of the parameter in the request.
     * @return A list of employees with the name specified in the request parameter.
     */
    @GetMapping(value = "/users", params = {"name"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User was updated!"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @Operation(summary = "This is endpoint to read all employees by name.", description = "Create request to get all employees by name.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllByName(@RequestParam(value = "name") String name) {
        List<Employee> list = service.getListAllByName(name);
        return list;
    }

    /**
     * This function returns a list of all employees who are full time
     *
     * @return A list of all employees who are full time.
     */
    @GetMapping(value = "/users/isFull")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User was updated!"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @Operation(summary = "This is endpoint to get all employees by value isFull.", description = "Create request to get all employees by isFull.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllByIsFullTrue() {
        return service.getAllByIsFullTrue();
    }

    /**
     * This function will generate a random password for the user with the given id
     *
     * @param id The id of the employee whose password is to be reset.
     * @return Employee
     */
    @PutMapping(value = "/users/password", params = {"id"})
    @Operation(summary = "This is endpoint to generate password for employee by ID.", description = "Create request to generate new Password for employee by ID.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
    public Employee generateRandomPassword(@RequestParam(value = "id") int id) {
        return service.generatePassword(id);
    }

    /**
     * This function updates the password of the employee with the given id
     *
     * @param employee The employee object that is passed in the request body.
     * @return The employee object is being returned.
     */
    @PatchMapping("/users/password")
    @Operation(summary = "This is endpoint to update password for employee by ID.", description = "Create request to update password for employee by ID.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
    public Employee updatePassword(@RequestBody Employee employee) {
        return service.updatePasswordById(employee);
    }


}
