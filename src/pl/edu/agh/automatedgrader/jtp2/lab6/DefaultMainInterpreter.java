package pl.edu.agh.automatedgrader.jtp2.lab6;

import org.junit.jupiter.api.Assertions;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Expression;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.MainInterpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultMainInterpreter implements MainInterpreter {
    public static void main(String[] args) {
        /* wektory wpisywane bezpośrednio do wyrażenia zapisywane w nawiasach kwadratowych, bez spacji wewnątrz,
        przecinek jako separator kolejnych liczb - jak w przykładzie poniżej */

        String expression = "[1,2,3] + x + [1,2,3] + z";
        DefaultEvaluator sentence = new DefaultEvaluator(expression);
        Map<String, Expression> variables = new HashMap<>();
        variables.put("x", new DefaultVector("[1.0,2.0,3.0]"));
        variables.put("z", new DefaultVector("[1.0,2.0,5.0]"));
        List<Double> result = sentence.interpret(variables);
        Assertions.assertEquals(result.get(0), 4.0);
        Assertions.assertEquals(result.get(1), 8.0);
        Assertions.assertEquals(result.get(2), 14.0);
    }
}
