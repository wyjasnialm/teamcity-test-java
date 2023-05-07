package pl.edu.agh.automatedgrader.jtp2.lab4.additionals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutorServiceScalarProduct {
    private static final int DIMENSION_THRESHOLD = 10_000;
    private List<Double> firstVector;
    private List<Double> secondVector;
    private double scalarProduct = 0;
    private static final Logger logger = Logger.getLogger(ExecutorServiceScalarProduct.class.getName());

    public ExecutorServiceScalarProduct(List<Double> firstVector, List<Double> secondVector) {
        if(firstVector.size() != secondVector.size()) {
            logger.log(Level.INFO, "Not matching dimensions - check vectors");
            System.exit(1);
        }

        this.firstVector = firstVector;
        this.secondVector = secondVector;
    }

    public double computeScalarProduct() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Double>> tasksList = new ArrayList<>();

        while(firstVector.size() > DIMENSION_THRESHOLD) {
            tasksList.add(new ExecutorServiceTask(
                    firstVector.subList(0, DIMENSION_THRESHOLD),
                    secondVector.subList(0, DIMENSION_THRESHOLD)
            ));

            firstVector = firstVector.subList(DIMENSION_THRESHOLD, firstVector.size());
            secondVector = secondVector.subList(DIMENSION_THRESHOLD, secondVector.size());
        }

        if(!firstVector.isEmpty()) tasksList.add(new ExecutorServiceTask(firstVector, secondVector));

        List<Future<Double>> calculatedScalars = null;
        try {
            calculatedScalars = executorService.invokeAll(tasksList);
        } catch (InterruptedException e) {
            logger.log(Level.INFO, "InterruptedException: ", e);
            Thread.currentThread().interrupt();
        }

        assert calculatedScalars != null;
        for(Future<Double> scalar : calculatedScalars) {
            try {
                scalarProduct += scalar.get();
            } catch (ExecutionException e) {
                logger.log(Level.INFO, "ExecutionException: ", e);
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "InterruptedException: ", e);
                Thread.currentThread().interrupt();
            }
        }

        executorService.shutdown();
        return scalarProduct;
    }
}
