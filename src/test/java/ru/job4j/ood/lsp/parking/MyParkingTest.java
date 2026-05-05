package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class MyParkingTest {
    private Parking parking;
    private Transport car;
    private Transport truck;
    private Transport largeTruck;

    @BeforeEach
    void setUp() {

        Set<Integer> truckColumns = Set.of(3, 4);
        parking = new MyParking(3, 5, truckColumns);
        car = new PassengerCar("М 543 КР 78");
        truck = new Truck("А 777 АА 116", 2);
        largeTruck = new Truck("С 888 СС 77", 3);
    }

    @Test
    void whenParkCarOnCarSpaceSuccess() {
        boolean parked = parking.occupySpace(car, 1, 1);
        assertThat(parked).isTrue();
        assertThat(parking.isVehicleParked("М 543 КР 78")).isTrue();
    }

    @Test
    void whenParkCarOnTruckSpaceFails() {
        boolean parked = parking.occupySpace(car, 1, 4);
        assertThat(parked).isFalse();
        assertThat(parking.isVehicleParked("М 543 КР 78")).isFalse();
    }

    @Test
    void whenParkTruckOnTruckSpaceSuccess() {
        boolean parked = parking.occupySpace(truck, 1, 4);
        assertThat(parked).isTrue();
        assertThat(parking.isVehicleParked("А 777 АА 116")).isTrue();
    }

    @Test
    void whenParkTruckOnConsecutiveCarSpacesSuccess() {
        boolean parked = parking.occupySpace(largeTruck, 1, 1);
        assertThat(parked).isTrue();
        assertThat(parking.isVehicleParked("С 888 СС 77")).isTrue();
    }

    @Test
    void whenParkTruckFailsWhenNoConsecutiveCarSpaces() {
        parking.occupySpace(car, 1, 1);
        boolean parked = parking.occupySpace(largeTruck, 1, 2);
        assertThat(parked).isFalse();
    }

    @Test
    void whenUnparkByRowColSuccess() {
        parking.occupySpace(car, 1, 1);
        boolean unparked = parking.unpark(1, 1);
        assertThat(unparked).isTrue();
        assertThat(parking.isVehicleParked("М 543 КР 78")).isFalse();
    }

    @Test
    void whenUnparkByRegistrationNumberSuccess() {
        parking.occupySpace(truck, 2, 4);
        boolean unparked = parking.unpark("А 777 АА 116");
        assertThat(unparked).isTrue();
        assertThat(parking.isVehicleParked("А 777 АА 116")).isFalse();
    }

    @Test
    void whenUnparkAlreadyFreePlaceThrowsException() {
        assertThatThrownBy(() -> parking.unpark(1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Вы неверно указали место. Данное место свободно.");
    }

    @Test
    void whenFindAllSuitableParkingForCar() {
        parking.occupySpace(car, 1, 1);
        parking.occupySpace(new PassengerCar("М 543 КР 78"), 1, 2);

        var suitable = parking.findAllSuitableParking(new PassengerCar("М 543 КР 78"));
        assertThat(suitable).hasSize(7);
    }

    @Test
    void whenGetAllSpace() {
        var suitable = parking.getAllSpace();
        assertThat(suitable).hasSize(15);
    }

    @Test
    void whenDisplayDoesNotThrowException() {
        parking.occupySpace(car, 1, 1);
        parking.occupySpace(truck, 2, 4);
        parking.occupySpace(largeTruck, 3, 1);
        assertThatCode(parking::display).doesNotThrowAnyException();
    }

    @Test
    void findAllSuitableParkingWhenTransportNull() {
        assertThatThrownBy(() -> parking.findAllSuitableParking(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Недопустимое значение аргумента: transport  не может быть null.");
    }

    @Test
    void whenUnparkRegistrationNumberNull() {
        assertThatThrownBy(() -> parking.unpark(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Недопустимое значение аргументов: registrationNumber не может быть null.");
    }

    @Test
    void whenisVehicleParkedNumberNull() {
        assertThatThrownBy(() -> parking.isVehicleParked(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Недопустимое значение аргумента:registrationNumber  не может быть null.");
    }

    @Test
    void occupySpaceWhenTransportAlreadyParked() {
        boolean parked = parking.occupySpace(car, 1, 1);
        assertThat(parked).isTrue();
        assertThatThrownBy(() -> parking.occupySpace(car, 2, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Данный транспорт уже запаркован.");
    }
}

