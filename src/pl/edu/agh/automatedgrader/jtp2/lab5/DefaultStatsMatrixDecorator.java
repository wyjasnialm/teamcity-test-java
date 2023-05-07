package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.StatsMatrixDecorator;

import java.util.Arrays;

public class DefaultStatsMatrixDecorator extends DefaultMatrix implements StatsMatrixDecorator {
    private DefaultMatrix defaultMatrix;

    public DefaultStatsMatrixDecorator(DefaultMatrix defaultMatrix) {
        this.defaultMatrix = defaultMatrix;
        this.defaultPrinter = defaultMatrix.defaultPrinter;
        this.rowCount = defaultMatrix.getRowCount();
        this.columnCount = defaultMatrix.getColumnCount();
    }

    @Override
    public String print() {
        StringBuilder string = new StringBuilder();
        double[][] matrix = new double[rowCount][columnCount];
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

        if(defaultPrinter.getClass().getCanonicalName().contains("DefaultOneLinePrinter")) {
            string.append(Arrays.deepToString(matrix));
        }
        else {
            for(double[] row : matrix) {
                for(double col : row) {
                    string.append(col);
                    string.append(' ');
                }
                string.append('\n');
            }
        }
        string.append(' ');
        string.append(super.print());

        return string.toString();
    }
}
