package ru.job4j.ood.isp.plant;

public class MimosaPudica implements Plant {

    @Override
    public void evaporationOfWater() {
        System.out.println("Мимщза испаряет воду");
    }

    @Override
    public void foldingLeaves() {
        System.out.println("Мимоза испугалась и сложилась");
    }

    @Override
    public void insectCatching() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void photosynthesis() {
        System.out.println("Мимоза осуществляет фотосинтез");
    }
}
