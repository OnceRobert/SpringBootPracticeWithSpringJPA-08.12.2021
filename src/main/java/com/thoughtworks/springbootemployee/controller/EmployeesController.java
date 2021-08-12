package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;


    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployeesList();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.findByID(employeeId);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam("gender") String givenGender) {
        return employeeService.findByGender(givenGender);
    }

    @GetMapping(params = {"index", "size"})
    public List<Employee> getEmployeesByPagination(@RequestParam int index, @RequestParam int size) {
        return employeeService.getByPage(index, size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }


    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated) {
        return employeeService.updateById(employeeId, employeeToBeUpdated);
    }

    @DeleteMapping(path = "/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteById(employeeId);
        return "Deleted Employee " + employeeId;
    }
}
