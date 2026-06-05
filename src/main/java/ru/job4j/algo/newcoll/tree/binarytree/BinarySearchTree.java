package ru.job4j.algo.newcoll.tree.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        if ((key.compareTo(node.key)) >= 0) {
            if (node.getRight() == null) {
                node.right = new Node(key);
                return true;
            }
            return put(node.right, key);
        } else {
            if (node.getLeft() == null) {
                node.left = new Node(key);
                return true;
            }
            return put(node.left, key);
        }
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if ((key.compareTo(node.key)) > 0) {
            return find(node.right, key);
        } else {
            return find(node.left, key);
        }
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root)) {
            result = remove(root, key);
        }
        return result;
    }

    private boolean remove(Node source, T key) {
        boolean result = true;
        Node current = source;
        Node parent = source;
        boolean isLeft = true;
        while (result && !Objects.equals(current.key, key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                isLeft = true;
                current = current.left;
            } else if (cmp > 0) {
                isLeft = false;
                current = current.right;
            }
            if (Objects.isNull(current)) {
                result = false;
            }
        }
        if (result) {
            if (Objects.isNull(current.left) && Objects.isNull(current.right)) {
                swap(isLeft, source, parent, current, null);
            } else if (Objects.nonNull(current.left) && Objects.isNull(current.right)) {
                swap(isLeft, source, parent, current, current.left);
            } else if (Objects.isNull(current.left)) {
                swap(isLeft, source, parent, current, current.right);
            } else {
                Node heir = getHeir(current);
                swap(isLeft, source, parent, current, heir);
                heir.left = current.left;
            }
        }
        if (current != null) {
            current.left = null;
            current.right = null;
            current.key = null;
            current = null;
        }
        return result;
    }

    private void swap(boolean isLeft, Node source, Node parent, Node current, Node equal) {
        if (Objects.equals(current, source)) {
            root = equal;
        } else if (isLeft) {
            parent.left = equal;
        } else {
            parent.right = equal;
        }
    }

    private Node getHeir(Node delNode) {
        Node nodeParent = delNode;
        Node node = delNode;
        Node current = delNode.right;
        while (current != null) {
            nodeParent = node;
            node = current;
            current = current.left;
        }
        if (node != delNode.right) {
            nodeParent.left = node.right;
            node.right = delNode.right;
        }
        return node;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> list = new ArrayList<>();
        inPreOrder(root, list);
        return list;
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public void clear() {
        Node node = root;
        clear(node);
        root = null;
    }

    private void clear(Node first) {
        if (first != null) {
            clear(first.left);
            clear(first.right);
            first.left = null;
            first.right = null;
            first.key = null;
        }
    }

    public List<T> inPostOrder() {
        List<T> list = new ArrayList<>();
        inPostOrder(root, list);
        return list;
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) && Objects.nonNull(minimum(root)) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        if (node != null) {
            if (node.getLeft() == null) {
                return node;
            }
            return minimum(node.getLeft());
        }
        return null;
    }

    public T maximum() {
        return Objects.nonNull(root) && Objects.nonNull(maximum(root)) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        if (node != null) {
            if (node.getRight() == null) {
                return node;
            }
            return maximum(node.getRight());
        }
        return null;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public Node getLeft() {
            return left;
        }

        @Override
        public Node getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}
