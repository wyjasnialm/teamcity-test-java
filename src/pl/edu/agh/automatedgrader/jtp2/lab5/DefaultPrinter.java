package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.Printer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class DefaultPrinter implements Printer {
    public String printSparseMatrix(Map<DefaultPair, Double> values) {
        StringBuilder string = new StringBuilder();
        List<Map.Entry<DefaultPair, Double>> sortedValues = new ArrayList<>(values.entrySet());
        sortedValues.sort(Map.Entry.comparingByKey(
                Comparator
                        .comparingInt(DefaultPair::getFirstNumber)
                        .thenComparingInt(DefaultPair::getSecondNumber)
        ));

        int lastColumnNumber = sortedValues.get(sortedValues.size() - 1).getKey().getSecondNumber();

        for(Map.Entry<DefaultPair, Double> entry : sortedValues) {
            if(entry.getValue() != 0.0) {
                string.append(entry.getKey().getFirstNumber());
                string.append(',');
                string.append(entry.getKey().getSecondNumber());
                string.append(':');
                string.append(entry.getValue());
                string.append(' ');
                if(entry.getKey().getSecondNumber() == lastColumnNumber) string.append('\n');
            }
        }

        return string.substring(0, string.length() - 1);
    }

    public String printDenseMatrix(double[][] values) {
        StringBuilder string = new StringBuilder();

        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[i].length; j++) {
                if(values[i][j] != 0.0) {
                    string.append(i);
                    string.append(',');
                    string.append(j);
                    string.append(':');
                    string.append(values[i][j]);
                    string.append(' ');
                }
            }
            string.append('\n');
        }

        return string.substring(0, string.length() - 1);
    }
}
