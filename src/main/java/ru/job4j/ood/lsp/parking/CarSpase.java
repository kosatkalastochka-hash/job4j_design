package ru.job4j.ood.lsp.parking;

public class CarSpase extends ParkingSpace {

    public CarSpase(int row, int cols) {
        super(row, cols);
    }

    @Override
    public boolean parkingSpaceCapacity(int size) {
        return size == 1;
    }
}
