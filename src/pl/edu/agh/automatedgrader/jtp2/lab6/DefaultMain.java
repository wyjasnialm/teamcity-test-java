package pl.edu.agh.automatedgrader.jtp2.lab6;

import org.junit.jupiter.api.Assertions;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultDenseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultOneLinePrinter;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Main;

public class DefaultMain implements Main {
    public static void main(String[] args) {
        DefaultMatrix defaultMatrix = new DefaultDenseMatrix(new double[][]
                {
                        { 1, 2, 4 },
                        { 1, 3, 5 } }, new DefaultOneLinePrinter());
        DefaultPrintingVisitor printingVisitor = new DefaultPrintingVisitor();
        defaultMatrix.acceptVisitor(printingVisitor);
        Assertions.assertEquals(printingVisitor.getString(), "[[1.0, 2.0, 4.0], [1.0, 3.0, 5.0]] not square matrix");
    }
}
