package ru.job4j.ood.lsp.parking;

public abstract class ParkingSpace implements Space {
    private final int row;
    private final int cols;
    private String registrationNumber;

    public ParkingSpace(int row, int cols) {
        this.cols = cols;
        this.row = row;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public boolean parkingSpaceCapacity(int size) {
        return false;
    }

    @Override
    public String getCarRegistrationNumber() {
        return this.registrationNumber;
    }

    @Override
    public int getCols() {
        return this.cols;
    }

    @Override
    public void setCarRegistrationNumber(String number) {
        this.registrationNumber = number;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(System.lineSeparator());
        sb.append("ParkingSpace{");
        sb.append("row=").append(row + 1);
        sb.append(", cols=").append(cols + 1);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Space other) {
        return this.row != other.getRow()
                ? Integer.compare(this.row, other.getRow())
                : Integer.compare(this.cols, other.getCols());
    }
}
