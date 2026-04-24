package ru.job4j.ood.srp.report;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarAdapter extends XmlAdapter<XMLGregorianCalendar, Calendar> {
    @Override
    public XMLGregorianCalendar marshal(Calendar v) throws Exception {
        if (v == null) {
            return null;
        }
        return DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) v);
    }

    @Override
    public Calendar unmarshal(XMLGregorianCalendar v) throws Exception {
        if (v == null) {
            return null;
        }
        return v.toGregorianCalendar();
    }
}
