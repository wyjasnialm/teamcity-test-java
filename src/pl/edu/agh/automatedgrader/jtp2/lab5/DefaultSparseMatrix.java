package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.SparseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.MatrixVisitor;

import java.util.Map;

public class DefaultSparseMatrix extends DefaultMatrixDecorator implements SparseMatrix {
    private Map<DefaultPair, Double> values;

    public DefaultSparseMatrix(Map<DefaultPair, Double> values, DefaultPrinter defaultPrinter) {
        this.values = values;
        this.defaultPrinter = defaultPrinter;

        int minRow = Integer.MAX_VALUE;
        int maxRow = 0;
        int minCol = Integer.MAX_VALUE;
        int maxCol = 0;

        for(Map.Entry<DefaultPair, Double> entry : values.entrySet()) {
            int tmpFirstNumber = entry.getKey().getFirstNumber();
            int tmpSecondNumber = entry.getKey().getSecondNumber();

            if(tmpFirstNumber > maxRow) maxRow = tmpFirstNumber;
            if(tmpFirstNumber < minRow) minRow = tmpFirstNumber;
            if(tmpSecondNumber > maxCol) maxCol = tmpSecondNumber;
            if(tmpSecondNumber < minCol) minCol = tmpSecondNumber;
        }

        this.rowCount = maxRow - minRow + 1;
        this.columnCount = maxCol - minCol + 1;
        this.matrixToBeDecorated = this;
    }

    public Map<DefaultPair, Double> getValues() {
        return values;
    }

    public DefaultPrinter getDefaultPrinter() {
        return defaultPrinter;
    }

    @Override
    public String print() {
        return defaultPrinter.printSparseMatrix(values);
    }

    @Override
    public void acceptVisitor(MatrixVisitor matrixVisitor) {
        matrixVisitor.visit(this);
    }
}
