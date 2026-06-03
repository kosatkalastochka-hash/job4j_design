package ru.job4j.algo.newcoll.tree;

import ru.job4j.list.SimpleQueue;
import ru.job4j.list.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public boolean add(Node<T> root, T parent, T kid) {
        if (root == null) {
            throw new IllegalArgumentException("Корневой элемент - null. Дерева не существует.");
        }
        Optional<Node<T>> desiredNode = findByKey(root, parent);
        if (desiredNode.isPresent() && findByKey(root, kid).isEmpty()) {
            desiredNode.get().getChildren().add(new Node<>(kid));
            return true;
        }
        return false;
    }

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        if (root == null) {
            throw new IllegalArgumentException("Корневой элемент - null. Дерева не существует.");
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            if (Objects.equals(key, currentNode.getValue())) {
                return Optional.of(currentNode);
            }
            if (currentNode.getChildren() != null) {
                for (Node<T> child : currentNode.getChildren()) {
                    stack.push(child);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        if (root == null) {
            throw new IllegalArgumentException("Корневой элемент - null. Дерева не существует.");
        }
        if (key.equals(root.getValue())) {
            return Optional.of(root);
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            int childIndex = findChildIndex(currentNode.getChildren(), key);
            if (childIndex >= 0) {
                return Optional.of(currentNode.getChildren().remove(childIndex));
            }
            if (currentNode.getChildren() != null) {
                for (Node<T> child : currentNode.getChildren()) {
                    stack.push(child);
                }
            }
        }
        return Optional.empty();
    }

    private int findChildIndex(List<Node<T>> children, T value) {
        for (int i = 0; i < children.size(); i++) {
            if (Objects.equals(children.get(i).getValue(), value)) {
                return i;
            }
        }
        return -1;
    }
}
