package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {

    //Получить все  парковочные  места
    List<Space> getAllSpace();

    //Поиск подходящего места
    List<Space> findAllSuitableParking(Transport transport);

    //Выезд с парковки
    boolean unpark(int row, int cols);

    boolean unpark(String registrationNumber);

    //Проверить, находится ли ТС на парковке
    boolean isVehicleParked(String registrationNumber);

    // Запорковаться
    boolean occupySpace(Transport transport, int row, int cols);

    // Вывод парковки в консоль в виде матрицы
    public void display();
}
