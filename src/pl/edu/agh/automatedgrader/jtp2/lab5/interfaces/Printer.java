package pl.edu.agh.automatedgrader.jtp2.lab5.interfaces;

import java.util.Map;

import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultPair;

public interface Printer
{
	String printSparseMatrix(Map<DefaultPair, Double> values);

	String printDenseMatrix(double[][] values);
}
