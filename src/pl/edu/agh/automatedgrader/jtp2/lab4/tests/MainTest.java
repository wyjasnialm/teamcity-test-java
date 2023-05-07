package pl.edu.agh.automatedgrader.jtp2.lab4.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import pl.edu.agh.automatedgrader.jtp2.lab4.DefaultMain;
import pl.edu.agh.automatedgrader.jtp2.lab4.interfaces.ScalarProductTask;

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
		List<Double> first = new ArrayList<>(Arrays.asList(Double.valueOf(1.0), Double.valueOf(2.0), Double.valueOf(3.0)));
		List<Double> second = new ArrayList<>(Arrays.asList(Double.valueOf(1.0), Double.valueOf(2.0), Double.valueOf(3.0)));
		DefaultMain defaultMain = new DefaultMain();
		double result = defaultMain.computeScalarProduct(first, second);
		Assertions.assertEquals(result, 14);
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test2()
	{
		List<Double> first = new ArrayList<>();
		List<Double> second = new ArrayList<>();
		for (int i = 0; i < 100; i++)
		{
			first.add(Double.valueOf(i));
			second.add(Double.valueOf(i));
		}
		DefaultMain defaultMain = new DefaultMain();
		double result = defaultMain.computeScalarProduct(first, second);
		Assertions.assertEquals(((ScalarProductTask) defaultMain.getForkJoinTask()).computeScalarProduct(), 328350.0);
		Assertions.assertEquals(result, 328350.0);
	}
}
