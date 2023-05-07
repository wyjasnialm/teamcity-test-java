package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.DenseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.MatrixVisitor;

public class DefaultDenseMatrix extends DefaultMatrixDecorator implements DenseMatrix {
    private double[][] values;

    public DefaultDenseMatrix(double[][] values, DefaultPrinter defaultPrinter) {
        this.values = values;
        this.defaultPrinter = defaultPrinter;
        this.rowCount = values.length;
        this.columnCount = values[0].length;
        this.matrixToBeDecorated = this;
    }

    public double[][] getValues() {
        return values;
    }

    public DefaultPrinter getDefaultPrinter() {
        return defaultPrinter;
    }

    @Override
    public String print() {
        return defaultPrinter.printDenseMatrix(values);
    }

    @Override
    public void acceptVisitor(MatrixVisitor matrixVisitor) {
        matrixVisitor.visit(this);
    }
}
