package pl.edu.agh.automatedgrader.jtp2.lab4.additionals;

import pl.edu.agh.automatedgrader.jtp2.lab4.interfaces.ScalarProductTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScalarProductAction extends RecursiveAction implements ScalarProductTask {
    private static final int DIMENSION_THRESHOLD = 10_000;
    private List<Double> firstVector;
    private List<Double> secondVector;
    private static double scalarProduct = 0;
    private static final Logger logger = Logger.getLogger(ScalarProductAction.class.getName());

    public ScalarProductAction(List<Double> firstVector, List<Double> secondVector) {
        if(firstVector.size() != secondVector.size()) {
            logger.log(Level.INFO, "Not matching dimensions - check vectors");
            System.exit(1);
        }

        this.firstVector = firstVector;
        this.secondVector = secondVector;
    }

    protected void compute() {
        if(firstVector.size() > DIMENSION_THRESHOLD) ForkJoinTask.invokeAll(createSubtasks());
        else computeScalarProduct();
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

        scalarProduct += sum;

        return sum;
    }

    private List<ScalarProductAction> createSubtasks() {
        List<ScalarProductAction> subtasksList = new ArrayList<>();

        subtasksList.add(new ScalarProductAction(
                firstVector.subList(0, firstVector.size() / 2),
                secondVector.subList(0, secondVector.size() / 2)
        ));
        subtasksList.add(new ScalarProductAction(
                firstVector.subList(firstVector.size() / 2, firstVector.size()),
                secondVector.subList(secondVector.size() / 2, secondVector.size())
        ));

        return subtasksList;
    }

    public double getScalarProduct() {
        return scalarProduct;
    }
}
