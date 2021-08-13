package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public class CompanyResponse {

    private Integer id;
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employeeList;

    public CompanyResponse() {

    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyResponse(Integer id, String companyName, List<Employee> employeeList) {
        this.id = id;
        this.companyName = companyName;
        this.employeeList = employeeList;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
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