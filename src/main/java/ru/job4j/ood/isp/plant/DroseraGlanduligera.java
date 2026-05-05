package ru.job4j.ood.isp.plant;

public class DroseraGlanduligera implements Plant {

    @Override
    public void evaporationOfWater() {
        System.out.println("Росянка испаряет воду");
    }

    @Override
    public void foldingLeaves() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insectCatching() {
        System.out.println("Росянка поймала муху");
    }

    @Override
    public void photosynthesis() {
        System.out.println("Росянка осуществляет фотосинтез");
    }
}
