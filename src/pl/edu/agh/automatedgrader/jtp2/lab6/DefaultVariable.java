package pl.edu.agh.automatedgrader.jtp2.lab6;

import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Expression;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Variable;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultVariable implements Variable, Expression {
    private String name;
    private static final Logger logger = Logger.getLogger(DefaultVariable.class.getName());

    public DefaultVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Double> interpret(Map<String, Expression> variables) {
        if(variables.get(name) == null) {
            logger.log(Level.INFO, "{0} not defined", name);
            System.exit(1);
        }
        return variables.get(name).interpret(variables);
    }
}
