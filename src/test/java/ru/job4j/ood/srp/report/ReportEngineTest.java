package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportEngineTest {
    Store store = new MemoryStore();
    Calendar now = Calendar.getInstance();
    DateTimeParser<Calendar> parser = new ReportDateTimeParser();
    Report engine = new ReportEngine(store, parser);

    @Test
    public void whenOldGenerated() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true, ReportType.HR, null, null)).isEqualTo(expected.toString());
    }

    @Test
    public void whenPredicateFalse() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> false, ReportType.PROGRAMMER, null, null)).isEqualTo(expected.toString());
    }

    @Test
    public void whenPredicateNull() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(null, ReportType.PROGRAMMER, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("""
                        Для построения отчета необходимо знать критерии отбора сотрудников, укажите filter.""")
        ;
    }

    @Test
    public void whenReportTypeAccountingUSDfromRUB() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary() * 65)
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true, ReportType.ACCOUNTING, Currency.USD, Currency.RUB)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportTypeAccountingEURfromRUB() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary() * 63)
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true, ReportType.ACCOUNTING, Currency.EUR, Currency.RUB)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportTypeAccountingUSDfromEUR() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary() * 1.0218)
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true, ReportType.ACCOUNTING, Currency.USD, Currency.EUR)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportTypeAccountingEURfromUSD() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary() * 0.9786)
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true, ReportType.ACCOUNTING, Currency.EUR, Currency.USD)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportTypeNull() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(employee -> true, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Для построения отчета необходимо знать его тип , укажите type.");
    }

    @Test
    public void whenReportTypeAccountingSourseNull() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        assertThatThrownBy(() -> engine.generate(employee -> true, ReportType.ACCOUNTING, null, Currency.RUB))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("""
                        В отчете ACCOUNTING  используется конвертация из исходной  
                        в целевую валюту - укажите:source,target.""");
    }

    @Test
    public void whenReportTypeAccountingTargetNull() {
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        assertThatThrownBy(() -> engine.generate(employee -> true, ReportType.ACCOUNTING, Currency.USD, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("""
                        В отчете ACCOUNTING  используется конвертация из исходной  
                        в целевую валюту - укажите:source,target.""");
    }

    @Test
    public void whenReportTypeHRSorted() {
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Nina", now, now, 95);
        Employee worker3 = new Employee("Igor", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true, ReportType.HR, null, null)).isEqualTo(expected.toString());
    }
}