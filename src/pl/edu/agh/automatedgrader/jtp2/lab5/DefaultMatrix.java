package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.Matrix;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.MatrixVisitor;

public abstract class DefaultMatrix implements Matrix {
    protected DefaultPrinter defaultPrinter;
    protected int rowCount;
    protected int columnCount;

    public String print() {
        return rowCount + " " + columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void acceptVisitor(MatrixVisitor matrixVisitor) {
        matrixVisitor.visit(this);
    }
}
