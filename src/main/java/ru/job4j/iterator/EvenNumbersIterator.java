package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;
    private final List<Integer> indices = new ArrayList<>();

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        initializationIndices();
    }

    private void initializationIndices() {
        for (int i = 0; i <= data.length - 1; i++) {
            if (data[i] % 2 == 0) {
                indices.add(i);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return index < indices.size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return data[indices.get(index++)];
    }
}
