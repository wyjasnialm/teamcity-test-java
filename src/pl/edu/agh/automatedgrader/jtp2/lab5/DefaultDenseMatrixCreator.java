package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.DenseMatrixCreator;

public class DefaultDenseMatrixCreator extends DefaultMatrixCreator implements DenseMatrixCreator {
    @Override
    public String print() {
        return defaultPrinter.printDenseMatrix(values);
    }
}
