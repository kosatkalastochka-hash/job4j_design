package ru.job4j.ood.isp.bird;

public interface Bird {

    //распушить перья
    void fluffUpFeathers();

    //идти
    void go();

    //отложить яйцо
    void  layAnEgg();

    //лететь
     void fly();

    //плыть
    void  swim();

    //действия - лететь,ходить и плыть присущи не всем птицам, для соблюдения принципа   ISP
    // они должны быть выделены в отдельные интерфейсы
}
