package ru.job4j.ood.lsp.parking;

import java.util.*;

public class Running {
    public static void main(String[] args) {

        Set<Integer> parkingSpaceForTruck = new HashSet<>();
        parkingSpaceForTruck.add(2);

        MyParking myParking = new MyParking(5, 5, parkingSpaceForTruck);

        Transport truck1 = new Truck("В 420 УС 116", 3);
        Transport truck2 = new Truck("С 777 МХ 154", 2);
        Transport truck3 = new Truck("А 931 КМ 50", 3);
        Transport car1 = new PassengerCar("Е 123 АВ 777");
        Transport car2 = new PassengerCar("Т 456 КО 78");
        Transport car3 = new PassengerCar("У 789 РМ 02");
        Transport car4 = new PassengerCar("Н 159 ОХ 93");
        Transport car5 = new PassengerCar("М 802 СТ 124");

        List<Space> spaces = myParking.getAllSpace();
        Collections.sort(spaces);
        System.out.printf("Все места парковки:%s %n", spaces);
        myParking.display();

        boolean occupy = myParking.occupySpace(truck1, 1, 3);
        myParking.occupySpace(truck3, 2, 3);
        System.out.println(occupy);

        List<Space> spaces1 = myParking.findAllSuitableParking(truck2);
        Collections.sort(spaces1);
        System.out.printf("Места подходящие для парковки %s:%s %n", truck2, spaces1);

        myParking.occupySpace(truck2, 1, 1);
        myParking.occupySpace(car1, 2, 1);
        myParking.occupySpace(car2, 3, 1);
        myParking.occupySpace(car3, 4, 1);
        myParking.occupySpace(car4, 5, 1);
        myParking.occupySpace(car5, 1, 5);
        myParking.display();
        System.out.println();

        boolean vehicleParked1 = myParking.isVehicleParked("Е 123 АВ 777");
        System.out.printf("Машина находится на парковке:%s %n %n", vehicleParked1);

        boolean vehicleParked2 = myParking.isVehicleParked("М 802 СТ 124");
        System.out.printf("Машина находится на парковке:%s %n %n", vehicleParked2);

        boolean unpark1 = myParking.unpark(5, 1);
        System.out.printf("Транспорт покинул парковку: %s %n", unpark1);

        boolean unpark2 = myParking.unpark("В 420 УС 116");
        System.out.printf("Транспорт покинул парковку: %s %n", unpark2);

        myParking.display();
    }
}
