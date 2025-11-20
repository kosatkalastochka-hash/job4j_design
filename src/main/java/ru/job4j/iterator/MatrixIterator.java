package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int lastRow = data.length - 1;
        for (int i = data.length - 1; i >= 0 && data[i].length == 0; i--) {
            lastRow = i;
        }
        while (data[row].length == 0 || column == data[row].length) {
            column = 0;
            row++;
        }
        return row < lastRow || column < data[lastRow].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}