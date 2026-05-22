package ru.job4j.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void whenSortedThenOk(){
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenArrayIsSorted(){
        int[] array = {1, 2, 3, 4, 5};
        int[] result = array.clone();
        Arrays.sort(result);
        boolean equals = Arrays.equals(Merge.mergesort(array), result);
        assertThat(equals).isTrue();
    }

    @Test
    void whenArrayWithRepeatingElements(){
        int[] array = {4, 2, 4, 1, 2, 3, 1};
        int[] result = array.clone();
        Arrays.sort(result);
        boolean equals = Arrays.equals(Merge.mergesort(array), result);
        assertThat(equals).isTrue();
    }

    @Test
    void whenЫingleElementArray(){
        int[] array = {42};
        int[] result = array.clone();
        Arrays.sort(result);
        boolean equals = Arrays.equals(Merge.mergesort(array), result);
        assertThat(equals).isTrue();
    }


}