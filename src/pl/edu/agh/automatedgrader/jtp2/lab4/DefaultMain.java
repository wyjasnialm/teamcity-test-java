package pl.edu.agh.automatedgrader.jtp2.lab4;

import pl.edu.agh.automatedgrader.jtp2.lab4.interfaces.Main;

import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class DefaultMain implements Main {
    private DefaultScalarProductTask defaultScalarProductTask;

    public double computeScalarProduct(List<Double> first, List<Double> second) {
        defaultScalarProductTask = new DefaultScalarProductTask(first, second);
        return defaultScalarProductTask.compute();
    }

    public ForkJoinTask<Double> getForkJoinTask() {
        return defaultScalarProductTask;
    }
}
