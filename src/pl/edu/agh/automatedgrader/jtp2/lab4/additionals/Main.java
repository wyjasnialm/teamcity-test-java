package pl.edu.agh.automatedgrader.jtp2.lab4.additionals;

import pl.edu.agh.automatedgrader.jtp2.lab4.DefaultMain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        List<Double> first = new ArrayList<>();
        List<Double> second = new ArrayList<>();

        for(int i = 0; i < 1_000_000; i++) {
            first.add(21.0);
            second.add(37.0);
        }

        DefaultMain defaultMain = new DefaultMain();

        long startTime = System.currentTimeMillis();
        double result1 = defaultMain.computeScalarProduct(first, second);
        long executionTime1 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        double result2 = computeScalarProductExecutorService(first, second);
        long executionTime2 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        double result3 = computeScalarProductCompletableFuture(first, second);
        long executionTime3 = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        double result4 = computeScalarProductAction(first, second);
        long executionTime4 = System.currentTimeMillis() - startTime;

        logger.log(Level.INFO,
                "\nFork/join (RecursiveTask): {0} [{1}s]\n" +
                        "ExecutorService: {2} [{3}s]\n" +
                        "CompletableFuture: {4} [{5}s]\n" +
                        "Fork/join (RecursiveAction): {6} [{7}s]",
                new Object[] {
                        result1, (double) executionTime1 / 1000,
                        result2, (double) executionTime2 / 1000,
                        result3, (double) executionTime3 / 1000,
                        result4, (double) executionTime4 / 1000
        });
    }

    public static double computeScalarProductExecutorService(List<Double> first, List<Double> second) {
        ExecutorServiceScalarProduct executorServiceScalarProduct = new ExecutorServiceScalarProduct(first, second);
        return executorServiceScalarProduct.computeScalarProduct();
    }

    public static double computeScalarProductCompletableFuture(List<Double> first, List<Double> second) {
        CompletableFutureScalarProduct completableFutureScalarProduct = new CompletableFutureScalarProduct(first, second);
        return completableFutureScalarProduct.computeScalarProduct();
    }

    public static double computeScalarProductAction(List<Double> first, List<Double> second) {
        ScalarProductAction scalarProductAction = new ScalarProductAction(first, second);
        scalarProductAction.compute();
        return scalarProductAction.getScalarProduct();
    }
}
