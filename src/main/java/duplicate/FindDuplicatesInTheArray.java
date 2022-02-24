package duplicate;

import duplicate.lib.ArraysLib;
import duplicate.thread.MyThread;
import java.util.List;
import java.util.Map;

public class FindDuplicatesInTheArray {
    private static final int[] ARRAY1 = {8, 3, 2, 7, 8, 2, 3, 1};
    private static final int[] ARRAY = ArraysLib.ARRAY_OF_1000_ELEMENTS;
    private static final Solutions solutions = new Solutions();

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        run1();
        run2();
        run3();
        run4();

        run1();
        run2();
        run3();
        run4();

        run1();
        run2();
        run3();
        run4();

        run1();
        run2();
        run3();
        run4();
    }

    private static void run1() {
        long start = System.currentTimeMillis();
        Map<Integer, Integer> list = solutions.solution1(ARRAY);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("how much time elapsed in milliseconds: " + elapsed);
        //list.forEach(System.out::println);
    }

    private static void run2() {
        long start = System.currentTimeMillis();
        List<Map.Entry<Integer, Long>> list = solutions.solution2(ARRAY);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("how much time elapsed in milliseconds: " + elapsed);
        //list.forEach(System.out::println);
    }

    private static void run3() {
        long start = System.currentTimeMillis();
        String string = solutions.solution3(ARRAY);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("how much time elapsed in milliseconds: " + elapsed);
        //System.out.println(string);
    }

    private static void run4() {
        long start = System.currentTimeMillis();
        Map<Integer, Integer> map = solutions.solution4(ARRAY);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("how much time elapsed in milliseconds: " + elapsed);
        //map.entrySet().forEach(System.out::println);
    }

}
