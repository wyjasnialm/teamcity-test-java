package pl.edu.agh.automatedgrader.jtp2.lab6;

import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Expression;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultVector implements Vector, Expression {
    private List<Double> vector = new ArrayList<>();
    private String vectorString;

    public DefaultVector(String vectorString) {
        this.vectorString = vectorString;
        for(String number : vectorString.substring(1, vectorString.length() - 1).split(",")) {
            vector.add(Double.parseDouble(number));
        }
    }

    public String getVectorString() {
        return vectorString;
    }

    public List<Double> interpret(Map<String, Expression> variables) {
        return vector;
    }
}
