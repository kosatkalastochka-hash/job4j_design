package ru.job4j.srp;

public class CargoDelivery {

    //  Необходимо создать интерфейс - CargoDelivery, а доставку разным видом транспорта прописать в классах наследниках
    // это позволит расширять виды транспорта без переписывания существующего функционала.
    public void deliveryByPlane(String route, String weiht) {
        System.out.printf("Груз массой %s доставлен по указанному маршруту %s самолетом", weiht, route);
    }

    public void deliveryByTrain(String route, String weiht) {
        System.out.printf("Груз массой %s доставлен по указанному маршруту %s поездом", weiht, route);
    }

    public void  deliveryByTruck(String route, String weiht) {
        System.out.printf("Груз массой %s доставлен по указанному маршруту %s  грузовиком", weiht, route);
    }
}
