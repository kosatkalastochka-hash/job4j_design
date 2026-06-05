package ru.job4j.algo.newcoll.tree.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

    class AvlTreeTest {

        private AvlTree<Integer> tree;

        @BeforeEach
        void setUp() {
            tree = new AvlTree<>();
        }

        @Test
        void whenInsertThenTreeContainsValue() {
            tree.insert(10);
            assertThat(tree.contains(10)).isTrue();
        }

        @Test
        void whenInsertDuplicateThenReturnsFalseAndTreeUnchanged() {
            tree.insert(10);
            boolean result = tree.insert(10);
            assertThat(result).isFalse();
            assertThat(tree.contains(10)).isTrue();
        }

        @Test
        void whenInsertMultipleValuesThenAllArePresent() {
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);
            tree.insert(20);
            tree.insert(40);
            tree.insert(60);
            tree.insert(80);

            assertThat(tree.contains(20)).isTrue();
            assertThat(tree.contains(40)).isTrue();
            assertThat(tree.contains(60)).isTrue();
            assertThat(tree.contains(80)).isTrue();
            assertThat(tree.contains(100)).isFalse();
        }

        @Test
        void whenRemoveLeafThenTreeNoLongerContainsIt() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);

            boolean removed = tree.remove(5);

            assertThat(removed).isTrue();
            assertThat(tree.contains(5)).isFalse();
            assertThat(tree.contains(10)).isTrue();
            assertThat(tree.contains(15)).isTrue();
        }

        @Test
        void whenRemoveNodeWithOneChildThenReplacedByChild() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(12);

            tree.remove(15);

            assertThat(tree.contains(15)).isFalse();
            assertThat(tree.contains(12)).isTrue();
            assertThat(tree.contains(10)).isTrue();
            assertThat(tree.contains(5)).isTrue();
        }

        @Test
        void whenRemoveNodeWithTwoChildrenThenReplacedByHeir() {
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);
            tree.insert(20);
            tree.insert(40);
            tree.insert(60);
            tree.insert(80);

            tree.remove(50);

            assertThat(tree.contains(50)).isFalse();
            assertThat(tree.contains(30)).isTrue();
            assertThat(tree.contains(70)).isTrue();
            assertThat(tree.contains(60)).isTrue(); // наследник
        }

        @Test
        void whenRemoveNonExistentElementThenReturnsFalse() {
            tree.insert(10);
            tree.insert(20);

            boolean removed = tree.remove(999);

            assertThat(removed).isFalse();
            assertThat(tree.contains(10)).isTrue();
            assertThat(tree.contains(20)).isTrue();
        }

        @Test
        void whenInsertAfterRemoveThenTreeBalancedAndContains() {
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);
            tree.remove(30);
            tree.insert(25);
            tree.insert(35);

            assertThat(tree.contains(25)).isTrue();
            assertThat(tree.contains(35)).isTrue();
            assertThat(tree.contains(30)).isFalse();
            assertThat(tree.balanceFactor(tree.root)).isBetween(-1, 1);
        }

        @Test
        void whenMinimumOnEmptyTreeThenReturnsNull() {
            Integer min = tree.minimum();
            assertThat(min).isNull();
        }

        @Test
        void whenMinimumOnNonEmptyTreeThenReturnsSmallest() {
            tree.insert(100);
            tree.insert(50);
            tree.insert(150);
            tree.insert(25);
            tree.insert(75);
            tree.insert(125);
            tree.insert(175);

            Integer min = tree.minimum();

            assertThat(min).isEqualTo(25);
        }

        @Test
        void whenInSymmetricalOrderThenReturnsSortedList() {
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);
            tree.insert(20);
            tree.insert(40);
            tree.insert(60);
            tree.insert(80);

            List<Integer> sorted = tree.inSymmetricalOrder();

            assertThat(sorted).containsExactly(20, 30, 40, 50, 60, 70, 80);
        }
    }
