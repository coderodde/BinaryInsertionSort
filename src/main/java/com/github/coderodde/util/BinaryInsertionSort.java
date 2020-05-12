package com.github.coderodde.util;

import java.util.Comparator;

/**
 * This class implements binary insertion sort, which, unlike conventional 
 * insertion sort, relies on binary search when searching the position to insert
 * the pivot element into.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (May 12, 2020) ~ initial version.
 * @since 1.6 (May 12, 20202)
 */
public final class BinaryInsertionSort {

    private BinaryInsertionSort() {}

    /**
     * Sorts the input range {@code array[fromIndex], ..., array[toIndex - 1]}
     * into ascending order.
     * 
     * @param <E> the array component type.
     * @param array the array holding the target range.
     * @param fromIndex the first inclusive range index.
     * @param toIndex the last exclusive range index.
     * @param comparaotr the comparator object.
     */
    public static <E> void sort(E[] array,
                                int fromIndex,
                                int toIndex,
                                Comparator<? super E> comparaotr) {

        for (int currentIndex = fromIndex + 1; 
                currentIndex < toIndex;
                currentIndex++) {
            final E pivot = array[currentIndex];

            int left = fromIndex;
            int right = currentIndex;

            while (left < right) {
                final int middle = (left + right) >>> 1;

                if (comparaotr.compare(pivot, array[middle]) < 0) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }

            assert left == right;

            final int n = currentIndex - left;

            switch (n) {
                case 2: array[left + 2] = array[left + 1];
                case 1: array[left + 1] = array[left];
                    break;

                default:
                    System.arraycopy(array, left, array, left + 1, n);
            }
        }
    }

    /**
     * Sorts the input array range into ascending order using a natural 
     * comparator.
     * 
     * @param <E> the array component type.
     * @param array the array holding the target range.
     * @param fromIndex the first inclusive range index.
     * @param toIndex the last exclusive range index.
     */
    public static <E> void sort(E[] array, int fromIndex, int toIndex) {
        sort(array, fromIndex, toIndex, new Comparator<E>() {
            @Override
            public int compare(final E elementLeft, final E elementRight) {
                return ((Comparable<E>) elementLeft).compareTo(elementRight);
            }
        });
    }

    /**
     * Sorts the entire input array into ascending order.
     * 
     * @param <E> the array component type.
     * @param array the target array to sort.
     */
    public static <E> void sort(E[] array) {
        sort(array, 0, array.length);
    }

    /**
     * Sorts the entire input array using the specifying comparator.
     * 
     * @param <E> the array component type.
     * @param array the target array to sort.
     * @param comparator the comparator object.
     */
    public static <E> void sort(E[] array, Comparator<? super E> comparator) {
        sort(array, 0, array.length, comparator);
    }
}
