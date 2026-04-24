package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JSONReportTest {
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
        Report engine = new JSONReport(store);
        String ex = """
                [
                  {
                    "name": "Иван",
                    "hired": {
                      "year": 2023,
                      "month": 3,
                      "dayOfMonth": 15,
                      "hourOfDay": 0,
                      "minute": 0,
                      "second": 0
                    },
                    "fired": {
                      "year": 2026,
                      "month": 1,
                      "dayOfMonth": 12,
                      "hourOfDay": 0,
                      "minute": 0,
                      "second": 0
                    },
                    "salary": 268.0
                  },
                  {
                    "name": "Виталий",
                    "hired": {
                      "year": 2023,
                      "month": 3,
                      "dayOfMonth": 15,
                      "hourOfDay": 0,
                      "minute": 0,
                      "second": 0
                    },
                    "fired": {
                      "year": 2026,
                      "month": 2,
                      "dayOfMonth": 12,
                      "hourOfDay": 0,
                      "minute": 0,
                      "second": 0
                    },
                    "salary": 134.0
                  }
                ]""";
        assertThat(engine.generate(em -> true, null, null, null)).isEqualTo(ex);
    }

    @Test
    void testSerializeNullFields() {
        Employee employee = new Employee("Test", null, null, 0);
        store.add(employee);
        Report engine = new JSONReport(store);

        assertFalse(engine.generate(em -> true, null, null, null).contains("null"));
        assertThat(engine.generate(em -> true, null, null, null).contains("0"));
        assertThat(engine.generate(em -> true, null, null, null).contains("Test"));
    }

    @Test
    void testEmptyList() throws Exception {
        Report engine = new JSONReport(store);
        String result = "[]";

        assertThat(engine.generate(em -> false, null, null, null)).isEqualTo(result);
    }
}