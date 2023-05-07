package pl.edu.agh.automatedgrader.jtp2.lab5.interfaces;

import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultPrinter;

public interface MatrixCreator
{
	DefaultMatrix createDefaultMatrix(double[][] values, DefaultPrinter defaultPrinter);
}
