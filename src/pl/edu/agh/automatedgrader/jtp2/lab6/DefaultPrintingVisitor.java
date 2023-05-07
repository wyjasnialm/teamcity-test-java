package pl.edu.agh.automatedgrader.jtp2.lab6;

import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.DenseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.SparseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.ParticularMatrixVisitor;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.PrintingVisitor;

import java.util.Arrays;

public class DefaultPrintingVisitor implements PrintingVisitor, ParticularMatrixVisitor {
    private String string;

    public String getString() {
        return string;
    }

    public void visit(DenseMatrix denseMatrix) {
        string = visitHelper((DefaultMatrix) denseMatrix);
    }

    public void visit(SparseMatrix sparseMatrix) {
        string = visitHelper((DefaultMatrix) sparseMatrix);
    }

    public void visit(DefaultMatrix defaultMatrix) {
        string = visitHelper(defaultMatrix);
    }

    private String visitHelper(DefaultMatrix defaultMatrix) {
        StringBuilder stringBuilder = new StringBuilder();
        int rows = defaultMatrix.getRowCount();
        int cols = defaultMatrix.getColumnCount();
        double[][] matrix = new double[rows][cols];
        String tmpString = defaultMatrix.print().replace("\n", "");
        tmpString = tmpString.substring(0, tmpString.length() - 1);

        for(String map : tmpString.split(" ")) {
            String[] tmpArray = map.split(":");
            String[] keys = tmpArray[0].split(",");
            int key1 = Integer.parseInt(keys[0]);
            int key2 = Integer.parseInt(keys[1]);
            double value = Double.parseDouble(tmpArray[1]);

            matrix[key1][key2] = value;
        }

        stringBuilder.append(Arrays.deepToString(matrix));
        stringBuilder.append(rows == cols ? " square matrix" : " not square matrix");
        return stringBuilder.toString();
    }
}
