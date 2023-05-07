package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.ConcatenationMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultConcatenationMatrix extends DefaultMatrix implements ConcatenationMatrix {
    private DefaultMatrix defaultMatrix;
    private DefaultMatrix defaultMatrix2;

    public DefaultConcatenationMatrix(DefaultMatrix defaultMatrix, DefaultMatrix defaultMatrix2) {
        this.defaultMatrix = defaultMatrix;
        this.defaultMatrix2 = defaultMatrix2;
    }

    public DefaultMatrix getMatrix1() {
        return defaultMatrix;
    }

    public DefaultMatrix getMatrix2() {
        return defaultMatrix2;
    }

    @Override
    public String print() {
        List<DefaultMatrix> list = new ArrayList<>();
        list.add(defaultMatrix);
        list.add(defaultMatrix2);

        StringBuilder string = new StringBuilder();

        for(DefaultMatrix listedMatrix : list) {
            double[][] matrix = new double[listedMatrix.getRowCount()][listedMatrix.getColumnCount()];
            String tmpString = listedMatrix.print().replace("\n", "");
            tmpString = tmpString.substring(0, tmpString.length() - 1);

            for(String map : tmpString.split(" ")) {
                String[] tmpArray = map.split(":");
                String[] keys = tmpArray[0].split(",");
                int key1 = Integer.parseInt(keys[0]);
                int key2 = Integer.parseInt(keys[1]);
                double value = Double.parseDouble(tmpArray[1]);

                matrix[key1][key2] = value;
            }

            string.append(Arrays.deepToString(matrix));
            string.append(' ');
        }

        return string.substring(0, string.length() - 1);
    }
}
