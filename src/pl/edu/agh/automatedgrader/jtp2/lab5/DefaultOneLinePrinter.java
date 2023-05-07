package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.OneLinePrinter;

import java.util.Map;

public class DefaultOneLinePrinter extends DefaultPrinter implements OneLinePrinter {
    @Override
    public String printSparseMatrix(Map<DefaultPair, Double> values) {
        return super.printSparseMatrix(values).replace("\n", "");
    }

    @Override
    public String printDenseMatrix(double[][] values) {
        return super.printDenseMatrix(values).replace("\n", "");
    }
}
