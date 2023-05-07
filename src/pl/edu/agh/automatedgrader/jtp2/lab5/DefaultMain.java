package pl.edu.agh.automatedgrader.jtp2.lab5;

import org.junit.jupiter.api.Assertions;
import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.Main;

import java.util.HashMap;
import java.util.Map;

public class DefaultMain implements Main {
    public static void main(String[] args) {
        Map<DefaultPair, Double> map = new HashMap<>();
        map.put(new DefaultPair(0, 0), 0.0);
        map.put(new DefaultPair(0, 1), 1.0);
        map.put(new DefaultPair(1, 0), 2.0);
        map.put(new DefaultPair(1, 1), 3.0);

        DefaultMatrix defaultMatrix = new DefaultSparseMatrix(map, new DefaultOneLinePrinter());
        defaultMatrix = new DefaultStatsMatrixDecorator(defaultMatrix);
        Assertions.assertEquals(defaultMatrix.print(), "[[0.0, 1.0], [2.0, 3.0]] 2 2");
    }
}
