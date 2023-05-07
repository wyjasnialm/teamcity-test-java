package pl.edu.agh.automatedgrader.jtp2.lab4.additionals;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutorServiceTask implements Callable<Double> {
    private List<Double> firstVector;
    private List<Double> secondVector;
    private static final Logger logger = Logger.getLogger(ExecutorServiceTask.class.getName());

    public ExecutorServiceTask(List<Double> firstVector, List<Double> secondVector) {
        if(firstVector.size() != secondVector.size()) {
            logger.log(Level.INFO, "Not matching dimensions - check vectors");
            System.exit(1);
        }

        this.firstVector = firstVector;
        this.secondVector = secondVector;
    }

    @Override
    public Double call() {
        double sum = 0;

        for(int i = 0; i < firstVector.size(); i++) {
            sum += firstVector.get(i) * secondVector.get(i);
        }

        return sum;
    }
}
