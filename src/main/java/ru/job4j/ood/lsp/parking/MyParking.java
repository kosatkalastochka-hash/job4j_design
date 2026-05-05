package ru.job4j.ood.lsp.parking;

import java.util.*;

public class MyParking implements Parking {

    private final ParkingSpace[][] parkingSpaces;
    private final int row;
    private final int cols;
    private final Set<Integer> parkingSpacesForTrucks; // Какие столбцы являются грузовыми  местами
    private Map<String, List<Space>> regNumberToSpace = new HashMap<>();

    public MyParking(int row, int cols, Set<Integer> parkingSpacesForTrucks) {
        this.cols = cols;
        this.row = row;
        this.parkingSpaces = new ParkingSpace[row][cols];
        this.parkingSpacesForTrucks = parkingSpacesForTrucks;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                if (parkingSpacesForTrucks.contains(j)) {
                    parkingSpaces[i][j] = new TruckSpace(i, j);
                } else {
                    parkingSpaces[i][j] = new CarSpase(i, j);
                }
            }
        }
    }

    @Override
    public List<Space> getAllSpace() {
        List<Space> spaces = new ArrayList<>();
        for (ParkingSpace[] parkingSpace : parkingSpaces) {
            Collections.addAll(spaces, parkingSpace);
        }
        return spaces;
    }

    @Override
    public List<Space> findAllSuitableParking(Transport transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Недопустимое значение аргумента: transport  не может быть null.");
        }
        List<Space> carSpace = findCarSpace();

        int size = transport.getSize();

        if (size == 1) {
            return carSpace.stream()
                    .filter(this::isSpaceFree)
                    .toList();
        } else {
            List<Space> allTruckSpace = new ArrayList<>();
            List<Space> truckSpaces = findTruckSpace().stream()
                    .filter(this::isSpaceFree)
                    .toList();
            allTruckSpace.addAll(truckSpaces);
            for (ParkingSpace[] parkingSpace : parkingSpaces) {
                allTruckSpace.addAll(findCarSpaceForTrucks(parkingSpace, size));
            }
            return allTruckSpace;
        }
    }

    private List<Space> findTruckSpace() {
        return getAllSpace().stream().filter(space -> space.parkingSpaceCapacity(2)).toList();
    }

    private boolean isSpaceFree(Space space) {
        return space != null && space.getCarRegistrationNumber() == null;
    }

    @Override
    public boolean unpark(int row, int cols) {
        Space space = parkingSpaces[row - 1][cols - 1];
        if (isSpaceFree(space)) {
            throw new IllegalArgumentException("Вы неверно указали место. Данное место свободно.");
        }
        String regNumber = space.getCarRegistrationNumber();
        List<Space> occupiedSpaces = regNumberToSpace.get(regNumber);
        if (occupiedSpaces != null) {
            for (Space occupiedSpace : occupiedSpaces) {
                occupiedSpace.setCarRegistrationNumber(null);
            }
            regNumberToSpace.remove(regNumber);
            return true;
        }
        return false;
    }

    @Override
    public boolean unpark(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Недопустимое значение аргументов: registrationNumber не может быть null.");
        }

        List<Space> occupiedSpaces = regNumberToSpace.get(registrationNumber);
        if (occupiedSpaces != null) {
            for (Space space : occupiedSpaces) {
                space.setCarRegistrationNumber(null);
            }
            regNumberToSpace.remove(registrationNumber);
            return true;
        }
        return false;
    }

    @Override
    public boolean isVehicleParked(String registrationNumber) {
        if (registrationNumber == null) {
            throw new IllegalArgumentException("Недопустимое значение аргумента:registrationNumber  не может быть null.");
        }
        return regNumberToSpace.containsKey(registrationNumber);
    }

    @Override
    public boolean occupySpace(Transport transport, int row, int cols) {
        Space space = parkingSpaces[row - 1][cols - 1];
        if (space == null || transport == null) {
            throw new IllegalArgumentException("Недопустимое значение аргумента: space,transport не может быть null.");
        }
        if (regNumberToSpace.containsKey(transport.getRegistrationNumber())) {
            throw new IllegalArgumentException("Данный транспорт уже запаркован.");
        }
        String currentRegistrationNumber = transport.getRegistrationNumber();
        List<Space> currentSpaces = new ArrayList<>();
        if (transport.getSize() == 1) {
            if (suitableParkingForCar(space)) {
                space.setCarRegistrationNumber(currentRegistrationNumber);
                currentSpaces.add(space);
                regNumberToSpace.put(currentRegistrationNumber, currentSpaces);
                return true;
            }
        } else {
            if (isSpaceFree(space) && parkingSpacesForTrucks.contains(space.getCols())) {
                space.setCarRegistrationNumber(currentRegistrationNumber);
                currentSpaces.add(space);
                regNumberToSpace.put(currentRegistrationNumber, currentSpaces);
                return true;
            } else {
                if (findConsecutiveCarSpaces(space, transport.getSize())) {
                    return occupyConsecutiveSpaces(space, transport);
                }
            }
        }
        return false;
    }

    @Override
    public void display() {
        int columnWidth = 16;
        System.out.println(" ".repeat(8 * cols) + "ПАРКОВКА" + " ".repeat(8 * cols));
        System.out.print("    ");
        for (int j = 0; j < cols; j++) {
            String type = parkingSpacesForTrucks.contains(j) ? "T" : "C";
            System.out.printf("%" + columnWidth + "s", type + (j + 1));
        }
        System.out.printf("%n" + "-".repeat(4 + columnWidth * cols) + "%n");

        for (int i = 0; i < row; i++) {
            System.out.printf("R%d |", i + 1);
            for (int j = 0; j < cols; j++) {
                Space space = parkingSpaces[i][j];
                if (isSpaceFree(space)) {
                    if (parkingSpacesForTrucks.contains(j)) {
                        System.out.printf("%" + columnWidth + "s", "[T]");
                    } else {
                        System.out.printf("%" + columnWidth + "s", "[C]");
                    }
                } else {
                    System.out.printf("%" + columnWidth + "s", space.getCarRegistrationNumber());
                }
            }
            System.out.println();
        }

    }

    private boolean suitableParkingForCar(Space space) {
        return isSpaceFree(space) && space.parkingSpaceCapacity(1);
    }

    private boolean findConsecutiveCarSpaces(Space space, int size) {
        int r = space.getRow();
        int c = space.getCols();

        if (c + size > cols) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            Space currentSpace = parkingSpaces[r][c + i];
            if (!isSpaceFree(currentSpace) || !currentSpace.parkingSpaceCapacity(1)) {
                return false;
            }
        }
        return true;
    }

    private boolean occupyConsecutiveSpaces(Space space, Transport transport) {
        int r = space.getRow();
        int c = space.getCols();
        int size = transport.getSize();
        List<Space> currentSpaces = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Space currentSpace = parkingSpaces[r][c + i];
            currentSpaces.add(currentSpace);
            currentSpace.setCarRegistrationNumber(transport.getRegistrationNumber());
        }
        regNumberToSpace.put(transport.getRegistrationNumber(), currentSpaces);
        return true;
    }

    private List<Space> findCarSpace() {
        return getAllSpace().stream().filter(space -> space.parkingSpaceCapacity(1)).toList();
    }

    private List<Space> findCarSpaceForTrucks(Space[] spaces, int size) {
        List<Space> result = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < spaces.length; i++) {
            Space currentSpace = spaces[i];
            if (isSpaceFree(currentSpace) && currentSpace.parkingSpaceCapacity(1)) {
                counter++;
                if (counter == size) {
                    result.add(spaces[i - size + 1]);
                    counter = 0;
                }
            } else {
                counter = 0;
            }
        }
        return result;
    }
}
