package ru.job4j.iterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;
    private final int lastRow;

    public MatrixIterator(int[][] data) {
        this.data = data;
        this.lastRow = initializationLastRow();
    }

    private int initializationLastRow() {
        int lastRow = data.length - 1;
        for (int i = data.length - 1; i >= 0 && data[i].length == 0; i--) {
            lastRow = i;
        }
        return lastRow;
    }

    @Override
    public boolean hasNext() {
        return row < lastRow || column < data[lastRow].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (data[row].length == 0 || column == data[row].length) {
            column = 0;
            row++;
        }
        return data[row][column++];
    }
}