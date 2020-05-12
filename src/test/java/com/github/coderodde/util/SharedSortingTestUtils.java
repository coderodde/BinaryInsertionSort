package com.github.coderodde.util;

import java.util.Random;

/**
 * This class provides shared facilities for unit testing.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (May 12, 2020) ~ initial version.
 * @since 1.6 (May 12, 2020)
 */
class SharedSortingTestUtils {

    static Integer[] getRandomIntegerArray(Random random, int length) {
        Integer[] array = new Integer[length];

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }   
}
