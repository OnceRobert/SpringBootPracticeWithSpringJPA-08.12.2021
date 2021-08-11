package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private List<Employees> employeesList = new ArrayList<>();
    @GetMapping
    public List<Employees> getAllEmployees() {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"female",900));

        return employeesList;
    }

    @GetMapping("/{employeeId}")
    public Employees getEmployeeById(@PathVariable Integer employeeId) {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"female",900));

        return employeesList.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping(params = {"gender"})
    public List<Employees> getEmployeesByGender(@RequestParam("gender") String givenGender) {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"female",900));

        return employeesList.stream()
                .filter(employee -> employee.getGender().equals(givenGender))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"index" , "size"})
    public List<Employees> getEmployeesByPagination(@RequestParam int index , @RequestParam int size ) {
        employeesList.add(new Employees(1,"Angelo", 23,"male",1000));
        employeesList.add(new Employees(2,"Angela", 26,"female",900));
        employeesList.add(new Employees(3,"Angelo2", 23,"male",1000));
        employeesList.add(new Employees(4,"Angela2", 26,"female",900));
        employeesList.add(new Employees(5,"Angelo3", 23,"male",1000));
        employeesList.add(new Employees(6,"Angela3", 26,"female",900));
        employeesList.add(new Employees(7,"Angela3", 26,"female",900));
        employeesList.add(new Employees(8,"Angela3", 26,"female",900));
        employeesList.add(new Employees(9,"Angela3", 26,"female",900));
        employeesList.add(new Employees(10,"Angela3", 26,"female",900));





        return employeesList.stream().skip((long) (index - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

}
