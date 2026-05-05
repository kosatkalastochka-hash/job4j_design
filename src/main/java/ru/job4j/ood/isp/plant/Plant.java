package ru.job4j.ood.isp.plant;

public interface Plant {

    // фотосинтез
    void photosynthesis();

    //испарение воды
    void evaporationOfWater();

    // ловля насекомого
    void insectCatching();

    //складывание листьев
    void foldingLeaves();

    //действия - ловля насекомого,складывание листьев присущи не всем растениям, для соблюдения принципа   ISP
    // они должны быть выделены в отдельные интерфейсы

}
