package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.MatrixCreator;

public abstract class DefaultMatrixCreator extends DefaultMatrix implements MatrixCreator {
    protected double[][] values;

    public DefaultMatrix createDefaultMatrix(double[][] values, DefaultPrinter defaultPrinter) {
        this.values = values;
        this.defaultPrinter = defaultPrinter;
        this.rowCount = values.length;
        this.columnCount = values[0].length;
        return this;
    }
}
