package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {
    private Gson gson;
    private final Store store;

    public JSONReport(Store store) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter, ReportType type, Currency sourse, Currency target) {
        List<Employee> employees = store.findBy(filter);
        return gson.toJson(employees);
    }
}


