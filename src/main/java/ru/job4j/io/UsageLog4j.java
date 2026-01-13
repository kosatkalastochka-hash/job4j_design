package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte value1 = 1;
        short value2 = 345;
        int value3 = 33678;
        long value4 = 434566778L;
        float value5 = 3566.87F;
        double value6 = 666787.54;
        boolean value7 = false;
        char value8 = 'v';

        LOG.debug("Info \n value1 : {} ,\n value2 : {},\n value3 : {},\n value4 : {},\n value5 : {},\n value6 : {},\n value7 : {},\n value8 : {}", value1, value2, value3, value4, value5, value6, value7, value8);
    }
}