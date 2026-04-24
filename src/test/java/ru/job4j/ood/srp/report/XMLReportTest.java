package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class XMLReportTest {
    private MemoryStore store;

    @BeforeEach
    void setUp() {
        store = new MemoryStore();
        Calendar hired = new GregorianCalendar(2023, Calendar.APRIL, 15);
        Calendar fired = new GregorianCalendar(2026, Calendar.FEBRUARY, 12);
        Calendar hired1 = new GregorianCalendar(2023, Calendar.APRIL, 15);
        Calendar fired1 = new GregorianCalendar(2026, Calendar.MARCH, 12);
        store.add(new Employee("Иван", hired, fired, 268));
        store.add(new Employee("Виталий", hired1, fired1, 134));
    }

    @Test
    void whenAccountantsGenerated() throws JAXBException {
        Report engine = new XMLReport(store);
        String ex = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee name="Иван" salary="268.0">
                        <hired>2023-04-15T00:00:00.000+03:00</hired>
                        <fired>2026-02-12T00:00:00.000+03:00</fired>
                    </employee>
                    <employee name="Виталий" salary="134.0">
                        <hired>2023-04-15T00:00:00.000+03:00</hired>
                        <fired>2026-03-12T00:00:00.000+03:00</fired>
                    </employee>
                </employees>
                """;
        assertThat(engine.generate(em -> true, null, null, null)).isEqualTo(ex);
    }

    @Test
    void testSerializeNullFields() {
        Employee employee = new Employee("Test", null, null, 0);
        store.add(employee);
        Report engine = new XMLReport(store);

        assertFalse(engine.generate(em -> true, null, null, null).contains("null"));
        assertThat(engine.generate(em -> true, null, null, null).contains("0"));
        assertThat(engine.generate(em -> true, null, null, null).contains("Test"));
    }

    @Test
    void testEmptyList() throws Exception {
        Report engine = new XMLReport(store);
        String result = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees/>
                """;

        assertThat(engine.generate(em -> false, null, null, null)).isEqualTo(result);
    }
}