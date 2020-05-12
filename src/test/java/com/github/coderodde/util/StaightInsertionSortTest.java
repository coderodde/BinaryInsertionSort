    package com.github.coderodde.util;

    import static com.github.coderodde.util.SharedSortingTestUtils.getRandomIntegerArray;
    import java.util.Arrays;
    import java.util.Random;
    import static org.junit.Assert.assertTrue;
    import org.junit.Test;

    /**
     * This unit test class tests the binary insertion sort algorithm 
     * ({@link com.github.coderodde.util.StaightInsertionSort}).
     * 
     * @author Rodion "rodde" Efremov
     * @version 1.6 (May 12, 2020) ~ initial version.
     * @since 1.6 (May 12, 2020)
     */
    public class StaightInsertionSortTest {

        public static final int REPETITIONS = 10_000;
        public static final int LENGTH = 100;

        @Test
        public void bruteForceTest() {
            long seed = System.currentTimeMillis();
            System.out.println("Seed = " + seed);
            Random random = new Random();

            for (int repetition = 0; repetition < REPETITIONS; repetition++) {
                Integer[] array1 = getRandomIntegerArray(random, LENGTH);
                Integer[] array2 = array1.clone();

                int index1 = random.nextInt(LENGTH), 
                    index2 = random.nextInt(LENGTH);

                int fromIndex = Math.min(index1, index2);
                int toIndex   = Math.max(index1, index2);

                Arrays.sort(array1, fromIndex, toIndex);
                StaightInsertionSort.sort(array2, fromIndex, toIndex);

                assertTrue(Arrays.equals(array1, array2));
            }
        }
    }
