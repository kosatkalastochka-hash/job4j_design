package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private final Store store;
    private final JAXBContext context;
    private final Marshaller marshaller;

    public XMLReport(Store store) {
        this.store = store;
        try {
            this.context = JAXBContext.newInstance(Employee.class, EmployeeList.class);
            this.marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Ошибка при инициализации JAXBContext", e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter, ReportType type, Currency sourse, Currency target) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            List<Employee> employees = store.findBy(filter);
            EmployeeList employeeList = new EmployeeList(employees);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(employeeList, writer);
            xml = writer.toString();
        } catch (JAXBException | IOException e) {
            throw new IllegalArgumentException("Ошибка при сериаллизации списка", e);
        }
        return xml;
    }
}
