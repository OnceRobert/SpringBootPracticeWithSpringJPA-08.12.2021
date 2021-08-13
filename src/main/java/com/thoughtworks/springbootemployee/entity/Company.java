package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String companyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Employee> employeeList;

    public Company(){
    }

    public Company(Integer id, String companyName, List<Employee> employeeList) {
        this.id = id;
        this.companyName = companyName;
        this.employeeList = employeeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Employee> getEmployeesList() {
        return employeeList;
    }

    public void setEmployeesList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
