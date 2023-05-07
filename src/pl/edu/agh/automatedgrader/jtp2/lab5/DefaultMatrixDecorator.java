package pl.edu.agh.automatedgrader.jtp2.lab5;

import pl.edu.agh.automatedgrader.jtp2.lab5.interfaces.MatrixDecorator;

public abstract class DefaultMatrixDecorator extends DefaultMatrix implements MatrixDecorator {
    protected DefaultMatrix matrixToBeDecorated = null;

    public DefaultMatrix getMatrixToBeDecorated() {
        return matrixToBeDecorated;
    }
}
