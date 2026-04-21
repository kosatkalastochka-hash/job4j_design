package ru.job4j.ood.srp.report;

public class ReportBuilder {

    private final StringBuilder sb = new StringBuilder();
    private final String columnSeparatorHeader = "; ";
    private final String columnSeparator = " ";
    private final String lineSeparator = System.lineSeparator();

    public ReportBuilder addHeader(String... columns) {
        sb.append(String.join(columnSeparatorHeader, columns))
                .append(lineSeparator);
        return this;
    }

    public ReportBuilder addRow(String... values) {
        sb.append(String.join(columnSeparator, values))
                .append(lineSeparator);
        return this;
    }

    public String build() {
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }
}
