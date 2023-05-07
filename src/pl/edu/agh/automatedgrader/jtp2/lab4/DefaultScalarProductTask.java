package pl.edu.agh.automatedgrader.jtp2.lab4;

import pl.edu.agh.automatedgrader.jtp2.lab4.interfaces.ScalarProductTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultScalarProductTask extends RecursiveTask<Double> implements ScalarProductTask {
    private static final int DIMENSION_THRESHOLD = 10_000;
    private String workerName = Thread.currentThread().getName().replace("ForkJoinPool.commonPool-", "");
    private List<Double> firstVector;
    private List<Double> secondVector;
    private static final Logger logger = Logger.getLogger(DefaultScalarProductTask.class.getName());

    public DefaultScalarProductTask(List<Double> firstVector, List<Double> secondVector) {
        if(firstVector.size() != secondVector.size()) {
            logger.log(Level.INFO, "Not matching dimensions - check vectors");
            System.exit(1);
        }

        this.firstVector = firstVector;
        this.secondVector = secondVector;
    }

    protected Double compute() {
        String currentWorkerName = Thread.currentThread().getName().replace("ForkJoinPool.commonPool-", "");
        if(!workerName.equals(currentWorkerName))
            logger.log(Level.INFO, "{0} stole task from {1}", new Object[] {currentWorkerName, workerName});

        if(firstVector.size() > DIMENSION_THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToDouble(ForkJoinTask::join)
                    .sum();
        } else return computeScalarProduct();
    }

    public List<Double> getFirstVector() {
        return firstVector;
    }

    public List<Double> getSecondVector() {
        return secondVector;
    }

    public double computeScalarProduct() {
        double sum = 0;

        for(int i = 0; i < firstVector.size(); i++) {
            sum += firstVector.get(i) * secondVector.get(i);
        }

        return sum;
    }

    private List<DefaultScalarProductTask> createSubtasks() {
        List<DefaultScalarProductTask> subtasksList = new ArrayList<>();

        subtasksList.add(new DefaultScalarProductTask(
                firstVector.subList(0, firstVector.size() / 2),
                secondVector.subList(0, secondVector.size() / 2)
        ));
        subtasksList.add(new DefaultScalarProductTask(
                firstVector.subList(firstVector.size() / 2, firstVector.size()),
                secondVector.subList(secondVector.size() / 2, secondVector.size())
        ));

        return subtasksList;
    }
}
