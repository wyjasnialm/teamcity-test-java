package pl.edu.agh.automatedgrader.jtp2.lab5.interfaces;

import java.util.Map;

import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultPair;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultPrinter;

public interface SparseMatrix extends Matrix
{
	Map<DefaultPair, Double> getValues();

	DefaultPrinter getDefaultPrinter();
}
