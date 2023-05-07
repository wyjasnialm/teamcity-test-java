package pl.edu.agh.automatedgrader.jtp2.lab6;

import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Expression;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Plus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultPlus implements Plus, Expression {
    protected Expression leftOperand;
    protected Expression rightOperand;
    private static final Logger logger = Logger.getLogger(DefaultPlus.class.getName());

    public DefaultPlus(Expression leftOperand, Expression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public Expression getLeftOperand() {
        return leftOperand;
    }

    public Expression getRightOperand() {
        return rightOperand;
    }

    public List<Double> interpret(Map<String, Expression> variables) {
        List<Double> leftOperandValue = leftOperand.interpret(variables);
        List<Double> rightOperandValue = rightOperand.interpret(variables);
        List<Double> newVector = new ArrayList<>();

        if(leftOperandValue.size() != rightOperandValue.size()) {
            logger.log(Level.INFO, "Not matching dimensions - check vectors");
            System.exit(1);
        }

        for(int i = 0; i < leftOperandValue.size(); i++) {
            newVector.add(i, leftOperandValue.get(i) + rightOperandValue.get(i));
        }

        return newVector;
    }
}
