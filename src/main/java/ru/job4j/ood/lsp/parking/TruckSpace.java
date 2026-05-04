package ru.job4j.ood.lsp.parking;

public class TruckSpace extends ParkingSpace {

    public TruckSpace(int row, int cols) {
        super(row, cols);
    }

    @Override
    public boolean parkingSpaceCapacity(int size) {
        return size > 1;
    }
}
