package pl.edu.agh.automatedgrader.jtp2.lab6;

import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Evaluator;
import pl.edu.agh.automatedgrader.jtp2.lab6.interfaces.Expression;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DefaultEvaluator implements Evaluator, Expression {
    private Expression syntaxTree;

    public DefaultEvaluator(String expression) {
        Stack<Expression> expressionStack = new Stack<>();
        int plusCounter = 0;

        for(String token : expression.split(" ")) {
            if(token.contains("[")) {
                expressionStack.push(new DefaultVector(token));
            }
            else if(token.equals("+")) {
                plusCounter++;
            }
            else expressionStack.push(new DefaultVariable(token));
        }

        for(int i = 0; i < plusCounter; i++) {
            Expression subExpression = new DefaultPlus(expressionStack.pop(), expressionStack.pop());
            expressionStack.push(subExpression);
        }

        syntaxTree = expressionStack.pop();
    }

    public Expression getSyntaxTree() {
        return syntaxTree;
    }

    public List<Double> interpret(Map<String, Expression> variables) {
        return syntaxTree.interpret(variables);
    }
}
