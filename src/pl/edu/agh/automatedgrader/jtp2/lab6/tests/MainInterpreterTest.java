package pl.edu.agh.automatedgrader.jtp2.lab6.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import pl.edu.agh.automatedgrader.jtp2.lab6.DefaultEvaluator;
import pl.edu.agh.automatedgrader.jtp2.lab6.DefaultVector;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Expression;

class MainInterpreterTest
{
	private MainInterpreterTest()
	{
		//empty
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test()
	{
		String expression = "[1,2,3] + x + y + z";
		DefaultEvaluator sentence = new DefaultEvaluator(expression);
		Map<String, Expression> variables = new HashMap<>();
		variables.put("x", new DefaultVector("[1.0,2.0,3.0]"));
		variables.put("y", new DefaultVector("[1.0,2.0,4.0]"));
		variables.put("z", new DefaultVector("[1.0,2.0,5.0]"));
		List<Double> result = sentence.interpret(variables);
		Assertions.assertEquals(result.get(0), 4.0);
		Assertions.assertEquals(result.get(1), 8.0);
		Assertions.assertEquals(result.get(2), 15.0);
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(1)
	void test2()
	{
		String expression = "[1,1,1] + [2,2,2] + [3,3,3]";
		DefaultEvaluator sentence = new DefaultEvaluator(expression);
		Map<String, Expression> variables = new HashMap<>();
		List<Double> result = sentence.interpret(variables);
		Assertions.assertEquals(result.get(0), 6.0);
		Assertions.assertEquals(result.get(1), 6.0);
		Assertions.assertEquals(result.get(2), 6.0);
	}
}
