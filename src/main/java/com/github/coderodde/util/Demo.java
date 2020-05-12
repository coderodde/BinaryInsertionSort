package com.github.coderodde.util;

import java.util.Random;

/**
 * This class implements a demonstration comparing performance of straight 
 * and binary insertion sort algorithms.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (May 12, 2020) ~ initial version.
 * @since 1.6 (May 12, 2020)
 */
public class Demo {

    public static final int REPETITIONS = 100_000;
    public static final int MAX_LENGTH_NORMAL = 2048;
    public static final int MAX_LENGTH_SMALL = 64;

    interface SortingAlgorithm<E> {
        public void sort(E[] array,
                        int fromIndex, 
                        int toIndex);
    }

    public static void main(String[] args) {
        long seed = System.currentTimeMillis();
        System.out.println("seed = " + seed);
        Random random = new Random(seed);

        ///////////////////////////////////////////
        System.out.println("--- Small arrays ---");
        warmupSmall(random, seed);
        benchmarkSmall(random, seed);
        ////////////////////////////////////////////
        System.out.println("--- Normal arrays ---");
        warmupNormal(random, seed);
        benchmarkNormal(random, seed);
    }

    static void warmupSmall(Random random, long seed) {
        random.setSeed(seed);
        System.out.print("Warmed up ");
        System.out.print(StaightInsertionSort.class.getSimpleName());

        warmup(MAX_LENGTH_SMALL,
               REPETITIONS,
               random,
               StaightInsertionSort::sort);

        random.setSeed(seed);
        System.out.print("Warmed up ");
        System.out.print(BinaryInsertionSort.class.getSimpleName());

        warmup(MAX_LENGTH_SMALL,
               REPETITIONS,
               random,
               BinaryInsertionSort::sort);
    }

    static void benchmarkSmall(Random random, long seed) {
        random.setSeed(seed);
        System.out.print("Benchmarked ");
        System.out.print(StaightInsertionSort.class.getSimpleName());

        benchmark(MAX_LENGTH_SMALL,
                  REPETITIONS,
                  random,
                  StaightInsertionSort::sort);

        random.setSeed(seed);
        System.out.print("Benchmarked ");
        System.out.print(BinaryInsertionSort.class.getSimpleName());

        benchmark(MAX_LENGTH_SMALL,
                  REPETITIONS,
                  random,
                  BinaryInsertionSort::sort);
    }

    static void warmupNormal(Random random, long seed) {
        random.setSeed(seed);
        System.out.print("Warmed up ");
        System.out.print(StaightInsertionSort.class.getSimpleName());

        warmup(MAX_LENGTH_NORMAL,
               REPETITIONS,
               random,
               StaightInsertionSort::sort);

        random.setSeed(seed);
        System.out.print("Warmed up ");
        System.out.print(BinaryInsertionSort.class.getSimpleName());

        warmup(MAX_LENGTH_NORMAL,
               REPETITIONS,
               random,
               BinaryInsertionSort::sort);
    }

    static void benchmarkNormal(Random random, long seed) {
        random.setSeed(seed);
        System.out.print("Benchmarked ");
        System.out.print(StaightInsertionSort.class.getSimpleName());

        benchmark(MAX_LENGTH_NORMAL,
                  REPETITIONS,
                  random,
                  StaightInsertionSort::sort);

        random.setSeed(seed);
        System.out.print("Benchmarked ");
        System.out.print(BinaryInsertionSort.class.getSimpleName());

        benchmark(MAX_LENGTH_NORMAL,
                  REPETITIONS,
                  random,
                  BinaryInsertionSort::sort);
    }

    static void perform(boolean isBenchmark,
                        int maxLength, 
                        int repetitions, 
                        Random random,
                        SortingAlgorithm<Integer> sortingAlgorithm) {

        long startTime = System.currentTimeMillis();

        for (int repetition = 0; repetition < repetitions; repetition++) {
            Integer[] array = getRandomIntegerArray(random, maxLength);

            int index1 = random.nextInt(maxLength);
            int index2 = random.nextInt(maxLength);

            int fromIndex = Math.min(index1, index2);
            int toIndex   = Math.max(index1, index2);

            sortingAlgorithm.sort(array, 
                                  fromIndex, 
                                  toIndex);
        }   

        System.out.println(" in " + (System.currentTimeMillis() - startTime) + 
                           " milliseconds.");
    }

    static void benchmark(int length, 
                          int repetitions, 
                          Random random, 
                          SortingAlgorithm sortingAlgorithm) {
        perform(true, length, repetitions, random, sortingAlgorithm);
    }

    static void warmup(int length, 
                       int repetitions, 
                       Random random, 
                       SortingAlgorithm sortingAlgorithm) {
        perform(false, length, repetitions, random, sortingAlgorithm);
    }

    static Integer[] getRandomIntegerArray(Random random, int length) {
        Integer[] array = new Integer[length];

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }
}
