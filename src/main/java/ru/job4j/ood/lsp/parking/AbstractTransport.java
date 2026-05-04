package ru.job4j.ood.lsp.parking;

public class AbstractTransport implements Transport {

    private final int size;
    private String registrationNumber;

    public AbstractTransport(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        this.size = 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transport{");
        sb.append("registrationNumber='").append(registrationNumber).append('\'');
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }

    public AbstractTransport(String registrationNumber, int size) {
        this.registrationNumber = registrationNumber;
        this.size = size;
    }

    @Override
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    @Override
    public int getSize() {
        return this.size;
    }

}
