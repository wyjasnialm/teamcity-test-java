package pl.edu.agh.automatedgrader.jtp2.lab5.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultConcatenationMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultDenseMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultDenseMatrixCreator;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultMatrix;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultMatrixCreator;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultMultiLinePrinter;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultOneLinePrinter;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultSparseMatrixCreator;
import pl.edu.agh.automatedgrader.jtp2.lab5.DefaultStatsMatrixDecorator;

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
		defaultMatrix = new DefaultStatsMatrixDecorator(defaultMatrix);
		Assertions.assertEquals(defaultMatrix.print(), "[[1.0, 2.0], [1.0, 3.0]] 2 2");
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test2()
	{
		DefaultMatrix defaultMatrix = new DefaultDenseMatrix(new double[][]
		{
				{ 1, 2 },
				{ 1, 3 } }, new DefaultMultiLinePrinter());
		defaultMatrix = new DefaultStatsMatrixDecorator(defaultMatrix);
		Assertions.assertEquals(defaultMatrix.print(), "1.0 2.0 \n" + "1.0 3.0 \n" + " 2 2");
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test3()
	{
		DefaultMatrix defaultMatrix = new DefaultDenseMatrix(new double[][]
		{
				{ 1, 2 },
				{ 1, 3 } }, new DefaultOneLinePrinter());
		DefaultMatrix defaultMatrix2 = new DefaultDenseMatrix(new double[][]
		{
				{ 2, 3 },
				{ 2, 4 } }, new DefaultOneLinePrinter());

		defaultMatrix = new DefaultConcatenationMatrix(defaultMatrix, defaultMatrix2);
		Assertions.assertEquals(defaultMatrix.print(), "[[1.0, 2.0], [1.0, 3.0]] [[2.0, 3.0], [2.0, 4.0]]");
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test4()
	{
		DefaultMatrixCreator defaultMatrixCreator = new DefaultDenseMatrixCreator();
		DefaultMatrix defaultMatrix1 = defaultMatrixCreator.createDefaultMatrix(new double[][]
		{
				{ 0, 2 },
				{ 1, 3 } }, new DefaultOneLinePrinter());
		Assertions.assertEquals(defaultMatrix1.getRowCount(), 2);
		defaultMatrixCreator = new DefaultSparseMatrixCreator();
		DefaultMatrix defaultMatrix2 = defaultMatrixCreator.createDefaultMatrix(new double[][]
		{
				{ 0, 2 },
				{ 1, 3 } }, new DefaultOneLinePrinter());
		Assertions.assertEquals(defaultMatrix2.getRowCount(), 2);
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test5()
	{
		DefaultMatrixCreator defaultMatrixCreator = new DefaultSparseMatrixCreator();
		DefaultMatrix defaultMatrix = defaultMatrixCreator.createDefaultMatrix(new double[][]
		{
				{ 0, 2 },
				{ 1, 3 } }, new DefaultOneLinePrinter());
		Assertions.assertEquals(defaultMatrix.print(), "0,1:2.0 1,0:1.0 1,1:3.0 ");
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test6()
	{
		DefaultMatrixCreator defaultMatrixCreator = new DefaultSparseMatrixCreator();
		DefaultMatrix defaultMatrix = defaultMatrixCreator.createDefaultMatrix(new double[][]
		{
				{ 0, 2 },
				{ 1, 3 } }, new DefaultMultiLinePrinter());
		Assertions.assertEquals(defaultMatrix.print(), "0,1:2.0 \n" + "1,0:1.0 1,1:3.0 ");
	}
}
