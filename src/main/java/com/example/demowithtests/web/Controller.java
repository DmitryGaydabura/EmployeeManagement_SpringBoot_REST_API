package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private final Service service;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.create(employee);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getById(id);
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {

        return service.updateById(id, employee);
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
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
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllByName(@RequestParam(value = "name") String name) {
        return service.getListAllByName(name);
    }

    /**
     * This function returns a list of all employees who are full time
     *
     * @return A list of all employees who are full time.
     */
    @GetMapping(value = "/users", params = {"isFull"})
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
    @ResponseStatus(HttpStatus.OK)
    public Employee updatePassword(@RequestBody Employee employee) {
        return service.updatePasswordById(employee);
    }


}
