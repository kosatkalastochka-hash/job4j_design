package ru.job4j.list;

import java.util.*;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;
    Node<E> last;

    @Override
    public void add(E value) {
        if (head == null) {
            head = new Node<>(value, null);
            last = head;
        } else {
            Node<E> newNode = new Node<>(value, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = head;
        for (int i = 0; i != index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = current.item;
                current = current.next;
                return result;
            }

        };
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}

