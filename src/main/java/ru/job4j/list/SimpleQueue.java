package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T result = output.pop();
        size--;
        return result;

    }

    public void push(T value) {
        for (int i = 0; i < size; i++) {
            input.push(output.pop());
        }
        output.push(value);
        for (int i = 0; i < size; i++) {
            output.push(input.pop());
        }
        size++;
    }
}
