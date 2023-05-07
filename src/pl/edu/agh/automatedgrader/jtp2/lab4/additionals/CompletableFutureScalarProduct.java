package pl.edu.agh.automatedgrader.jtp2.lab4.additionals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompletableFutureScalarProduct {
    private static final int DIMENSION_THRESHOLD = 10_000;
    private List<Double> firstVector;
    private List<Double> secondVector;
    private static final Logger logger = Logger.getLogger(CompletableFutureScalarProduct.class.getName());

    public CompletableFutureScalarProduct(List<Double> firstVector, List<Double> secondVector) {
        if(firstVector.size() != secondVector.size()) {
            logger.log(Level.INFO, "Not matching dimensions - check vectors");
            System.exit(1);
        }

        this.firstVector = firstVector;
        this.secondVector = secondVector;
    }

    public double computeScalarProduct() {
        List<CompletableFuture<Double>> futureScalarProducts = new ArrayList<>();

        while(firstVector.size() > DIMENSION_THRESHOLD) {
            futureScalarProducts.add(CompletableFuture.supplyAsync(() -> computePartialScalarProduct(
                    firstVector.subList(0, DIMENSION_THRESHOLD),
                    secondVector.subList(0, DIMENSION_THRESHOLD)
            )));

            firstVector = firstVector.subList(DIMENSION_THRESHOLD, firstVector.size());
            secondVector = secondVector.subList(DIMENSION_THRESHOLD, secondVector.size());
        }

        if(!firstVector.isEmpty()) {
            futureScalarProducts.add(
                    CompletableFuture.supplyAsync(() -> computePartialScalarProduct(firstVector, secondVector))
            );
        }

        CompletableFuture<Void> calculatedScalarProducts = CompletableFuture.allOf(
                futureScalarProducts.toArray(new CompletableFuture[0])
        );

        try {
            calculatedScalarProducts.get();
        } catch (ExecutionException e) {
            logger.log(Level.INFO, "ExecutionException: ", e);
        } catch (InterruptedException e) {
            logger.log(Level.INFO, "InterruptedException: ", e);
            Thread.currentThread().interrupt();
        }

        return futureScalarProducts.stream()
                .map(CompletableFuture::join)
                .mapToDouble(Double::valueOf)
                .sum();
    }

    private double computePartialScalarProduct(List<Double> firstVector, List<Double> secondVector) {
        double sum = 0;

        for(int i = 0; i < firstVector.size(); i++) {
            sum += firstVector.get(i) * secondVector.get(i);
        }

        return sum;
    }
}
