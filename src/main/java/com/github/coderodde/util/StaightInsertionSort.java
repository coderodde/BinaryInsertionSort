package com.github.coderodde.util;

import java.util.Comparator;


/**
 * This class implements straight insertion sort, which differs from ordinary 
 * insertion sort by the fact that it does not shift the subranges to shift by
 * swapping the element, but instead by saving the rightmost element, shifting
 * everything in the shift range one position to the right and inserting the
 * saved element into its correct position.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (May 11, 2020) ~ initial version.
 * @see 1.6 (May 11, 2020)
 */
public final class StaightInsertionSort {

    private StaightInsertionSort() {}

    /**
     * Sorts the input array range into ascending order using an explicit 
     * comparator.
     * 
     * @param <E> the array component type.
     * @param array the array holding the target range.
     * @param fromIndex the first inclusive range index.
     * @param toIndex the last exclusive range index.
     * @param comparator the comparator.
     */
    public static <E> void sort(E[] array, 
                                int fromIndex,
                                int toIndex,
                                Comparator<? super E> comparator) {

        for (int i = fromIndex + 1; i < toIndex; i++) {
            final E targetElement = array[i];
            int j = i - 1;

            while (j >= fromIndex 
                    && comparator.compare(array[j], targetElement) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = targetElement;
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

    public static <E> void sort(E[] array) {
        sort(array, 0, array.length);
    }

    public static <E> void sort(E[] array, Comparator<? super E> comparator) {
        sort(array, 0, array.length, comparator);
    }
}
