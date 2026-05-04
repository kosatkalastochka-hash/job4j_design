package ru.job4j.ood.lsp.parking;

public interface Space extends Comparable<Space> {

    int getRow();

    int getCols();

    void setCarRegistrationNumber(String registrationNumber);

    String getCarRegistrationNumber();

    //Проверка размера парковочного места
    boolean parkingSpaceCapacity(int size);
}
