package ru.job4j.algo.newcoll.tree;

import ru.job4j.list.SimpleQueue;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils<T> {

    public int countNode(Node<T> root) {
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        int count = 0;
        if (root == null) {
            throw new IllegalArgumentException("Корневой элемент - null. Дерева не существует.");
        }
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            count++;
            if (currentNode.getChildren() != null) {
                for (Node<T> child : currentNode.getChildren()) {
                    queue.push(child);
                }
            }
        }
        return count;
    }

    public Iterable<T> findAll(Node<T> root) {
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> elements = new ArrayList<>();
        if (root == null) {
            throw new IllegalArgumentException("Корневой элемент - null. Дерева не существует.");
        }
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            elements.add(currentNode.getValue());
            if (currentNode.getChildren() != null) {
                for (Node<T> child : currentNode.getChildren()) {
                    queue.push(child);
                }
            }
        }
        return elements;
    }
}
