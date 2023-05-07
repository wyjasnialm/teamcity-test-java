package pl.edu.agh.automatedgrader.jtp2.lab5.interfaces;

import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultPrinter;

public interface DenseMatrix extends Matrix
{
	public double[][] getValues();

	DefaultPrinter getDefaultPrinter();
}
