package pl.edu.agh.automatedgrader.jtp2.lab1.additionals;

import org.junit.jupiter.api.Assertions;
import pl.edu.agh.automatedgrader.jtp2.lab1.DefaultMain;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        DefaultMain main = new DefaultMain();

        long startTime = System.currentTimeMillis();
        List<Integer> list1 = main.sum(10_000, 1, 1, 2);
        long executionTime1 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        List<Integer> list2 = main.sum(5_000, 2, 1, 2);
        long executionTime2 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        List<Integer> list3 = main.sum(2_000, 5, 1, 2);
        long executionTime3 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        List<Integer> list4 = main.sum(1_000, 10, 1, 2);
        long executionTime4 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        List<Integer> list5 = main.sum(500, 20, 1, 2);
        long executionTime5 = System.currentTimeMillis() - startTime;

        logger.log(Level.INFO,
                "\n1 Thread: {0}s\n" +
                        "2 Threads: {1}s\n" +
                        "5 Threads: {2}s\n" +
                        "10 Threads: {3}s\n" +
                        "20 Threads: {4}s",
                new Object[] {
                        (double) executionTime1 / 1000,
                        (double) executionTime2 / 1000,
                        (double) executionTime3 / 1000,
                        (double) executionTime4 / 1000,
                        (double) executionTime5 / 1000
        });

        Assertions.assertTrue(list1.containsAll(list2));
        Assertions.assertTrue(list2.containsAll(list3));
        Assertions.assertTrue(list3.containsAll(list4));
        Assertions.assertTrue(list4.containsAll(list5));
        Assertions.assertTrue(list5.containsAll(list1));
    }
}
