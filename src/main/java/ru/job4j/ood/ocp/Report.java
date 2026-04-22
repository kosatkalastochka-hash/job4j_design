package ru.job4j.ood.ocp;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class Report {

    public String generate(List<Employee> employees) {
        StringBuilder builder = new StringBuilder();
        List<Employee> employeesFiltered = employees.stream()
                .filter(employee -> employee.getSalary() > 100)
                //  Нарушение принципа OCP.  Если поменяется критерий отбора прийдется переписывать метод
                //  или создавать еще один. Вернее было бы передавать в параметры метода предикат
                //  для фильтрации сотрудников.
                .toList();

        for (Employee employee : employeesFiltered) {
            builder.append("Name; Salary")
                    .append(System.lineSeparator())
                    .append(employee.getName())
                    .append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}