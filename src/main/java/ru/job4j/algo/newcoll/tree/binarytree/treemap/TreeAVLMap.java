package ru.job4j.algo.newcoll.tree.binarytree.treemap;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {
    private Node root;

    public boolean insert(T key, V value) {
        if (Objects.nonNull(value) && Objects.nonNull(key)) {
            root = insert(root, key, value);
            return true;
        }
        return false;
    }

    private Node insert(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = insert(node.left, key, value);
            } else if (comparisonResult > 0) {
                node.right = insert(node.right, key, value);
            } else {
                node.value = value;
                return node;
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    public boolean remove(T key) {
        boolean[] removed = {false};
        if (Objects.nonNull(key) && Objects.nonNull(root)) {
            root = remove(root, key, removed);
            return removed[0];
        }
        return false;
    }

    private Node remove(Node node, T key, boolean[] removed) {
        if (node == null) {
            return null;
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, key, removed);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, key, removed);
        } else {
            removed[0] = true;
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir, removed);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir, removed);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    public T minimum() {
        return Objects.nonNull(root) && Objects.nonNull(minimum(root)) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        if (node != null) {
            if (node.left == null) {
                return node;
            }
            return minimum(node.left);
        }
        return null;
    }

    public T maximum() {
        return Objects.nonNull(root) && Objects.nonNull(maximum(root)) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        if (node != null) {
            if (node.right == null) {
                return node;
            }
            return maximum(node.right);
        }
        return null;
    }

    public V get(T key) {
        if (Objects.nonNull(key)) {
            return find(root, key);
        }
        return null;
    }

    private V find(Node localRoot, T key) {
        if (localRoot == null) {
            return null;
        }
        int cmp = key.compareTo(localRoot.key);
        if (cmp < 0) {
            return find(localRoot.left, key);
        }
        if (cmp > 0) {
            return find(localRoot.right, key);
        }
        return localRoot.value;
    }

    public Set<T> keySet() {
        Set<T> result = new HashSet<>();
        return inSymmetricalOrder(root, result);
    }

    private Set<T> inSymmetricalOrder(Node localRoot, Set<T> set) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, set);
            set.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, set);
        }
        return set;
    }

    public Collection<V> values() {
        Collection<V> result = new ArrayList<>();
        inSymmetricalOrder(root, result);
        return result;
    }

    private Collection<V> inSymmetricalOrder(Node localRoot, Collection<V> values) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, values);
            values.add(localRoot.value);
            inSymmetricalOrder(localRoot.right, values);
        }
        return values;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor <= 0) {
                result = rightRotation(node);
            } else {
                result = leftRightCase(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? 0 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? 0 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}