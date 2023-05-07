package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.SparseMatrixCreator;

import java.util.HashMap;
import java.util.Map;

public class DefaultSparseMatrixCreator extends DefaultMatrixCreator implements SparseMatrixCreator {
    @Override
    public String print() {
        Map<DefaultPair, Double> valuesAsMap = new HashMap<>();

        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                valuesAsMap.put(new DefaultPair(i, j), values[i][j]);
            }
        }

        return defaultPrinter.printSparseMatrix(valuesAsMap);
    }
}
