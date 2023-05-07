package pl.edu.agh.automatedgrader.jtp2.lab6.interfaces;

import java.util.List;
import java.util.Map;

public interface Expression
{
	List<Double> interpret(Map<String, Expression> variables);
}
