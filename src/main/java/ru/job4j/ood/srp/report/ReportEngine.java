package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;
    private final ReportBuilder builder;

    public ReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = new InMemoryCurrencyConverter();
        this.builder = new ReportBuilder();
    }

    @Override
    public String generate(Predicate<Employee> filter, ReportType type, Currency source, Currency target) {
        if (filter == null) {
            throw new IllegalArgumentException(
                    """
                    Для построения отчета необходимо знать критерии отбора сотрудников, укажите filter.""");
        }
        if (type == null) {
            throw new IllegalArgumentException("Для построения отчета необходимо знать его тип , укажите type.");
        }

        List<Employee> employees = store.findBy(filter);
        return switch (type) {
            case ACCOUNTING -> {
                if (source == null || target == null) {
                    throw new IllegalArgumentException(
                            """
                                    В отчете ACCOUNTING  используется конвертация из исходной  
                                    в целевую валюту - укажите:source,target.""");
                }
                yield generateAccountingReport(employees, source, target);
            }
            case HR -> generateHRReport(employees);
            case PROGRAMMER -> generateProgrammerReport(employees);
        };
    }

    private String generateAccountingReport(List<Employee> employees, Currency source, Currency target) {
        builder.addHeader("Name", "Hired", "Fired", "Salary");
        for (Employee employee : employees) {
            builder.addRow(
                    employee.getName(),
                    dateTimeParser.parse(employee.getHired()),
                    dateTimeParser.parse(employee.getFired()),
                    String.valueOf(converter.convert(source, employee.getSalary(), target)));
        }
        return builder.build();
    }

    private String generateHRReport(List<Employee> employees) {
        builder.addHeader("Name", "Salary");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(employee ->
                        builder.addRow(
                                employee.getName(),
                                String.valueOf(employee.getSalary())
                        )
                );
        return builder.build();
    }

    private String generateProgrammerReport(List<Employee> employees) {
        builder.addHeader("Name", "Hired", "Fired", "Salary");
        for (Employee employee : employees) {
            builder.addRow(employee.getName(),
                    dateTimeParser.parse(employee.getHired()),
                    dateTimeParser.parse(employee.getFired()),
                    String.valueOf(employee.getSalary()));
        }
        return builder.build();
    }
}
