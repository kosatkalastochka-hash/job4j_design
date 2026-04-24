package ru.job4j.ood.srp.report;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;

@XmlRootElement(name = "employees")
public class EmployeeList {

    private List<Employee> employees;

    @XmlElement(name = "employee")
    public List<Employee> getEmployees() {
        return employees;
    }

    public EmployeeList() {
    }

    public EmployeeList(List<Employee> employees) {
        this.employees = employees;
    }
}
