package pl.edu.agh.automatedgrader.jtp2.lab6.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultDenseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultOneLinePrinter;
import pl.edu.agh.automatedgrader.jtp2.lab6.DefaultPrintingVisitor;

class MainTest
{
	private MainTest()
	{
		//empty
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test()
	{
		DefaultMatrix defaultMatrix = new DefaultDenseMatrix(new double[][]
		{
				{ 1, 2 },
				{ 1, 3 } }, new DefaultOneLinePrinter());
		DefaultPrintingVisitor printingVisitor = new DefaultPrintingVisitor();
		printingVisitor.visit(defaultMatrix);
		Assertions.assertEquals(printingVisitor.getString(), "[[1.0, 2.0], [1.0, 3.0]] square matrix");
	}
}
