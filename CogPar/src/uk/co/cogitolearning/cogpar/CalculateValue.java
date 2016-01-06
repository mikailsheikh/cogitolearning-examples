package uk.co.cogitolearning.cogpar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import static uk.co.cogitolearning.cogpar.FunctionExpressionNode.*;

public class CalculateValue {

    public CalculateValue() {
    }

    static double getValue(ArrayList<ExpressionNode> list) {
        // https://en.wikipedia.org/wiki/Polish_notation
        Collections.reverse(list); // Scan the given prefix expression from right to left
        Stack<Double> stack = new Stack<Double>();

        for(ExpressionNode node: list){
            if (node.getType() == ExpressionNode.MULTIPLICATION_NODE){
                double operand1 = stack.pop();
                double operand2 = stack.pop();
                stack.push(operand1 * operand2);
                continue;
            }
            if (node.getType() == ExpressionNode.DIVISION_NODE){
                double operand1 = stack.pop();
                double operand2 = stack.pop();
                stack.push(operand1 / operand2);
                continue;
            }

            if (node.getType() == ExpressionNode.CONSTANT_NODE){
                stack.push(node.getValue());
                continue;
            }
            if (node.getType() == ExpressionNode.ADDITION_NODE){
                double operand1 = stack.pop();
                double operand2 = stack.pop();

                stack.push(operand1 + operand2);
                continue;
            }
            if (node.getType() == ExpressionNode.FUNCTION_NODE){
                double operand1 = stack.pop();
                stack.push(functionGetValue(((FunctionExpressionNode) node).getFunction(), operand1));
                continue;
            }
            if (node.getType() == ExpressionNode.EXPONENTIATION_NODE){
                double base = stack.pop();
                double exponent = stack.pop();
                stack.push(Math.pow(base, exponent));
                continue;
            }
            if (node.getType() == ExponentiationExpressionNode.VARIABLE_NODE){
                stack.push(node.getValue());
                continue;
            }
            throw new EvaluationException("Invalid node " + node + "!");
        }

        return stack.pop();
    }

    static public double functionGetValue(int function, double argument)
    {
        switch (function)
        {
            case SIN:
                return Math.sin(argument);
            case COS:
                return Math.cos(argument);
            case TAN:
                return Math.tan(argument);
            case ASIN:
                return Math.asin(argument);
            case ACOS:
                return Math.acos(argument);
            case ATAN:
                return Math.atan(argument);
            case SQRT:
                return Math.sqrt(argument);
            case EXP:
                return Math.exp(argument);
            case LN:
                return Math.log(argument);
            case LOG:
                return Math.log(argument) * 0.43429448190325182765;
            case LOG2:
                return Math.log(argument) * 1.442695040888963407360;
        }

        throw new EvaluationException("Invalid function id "+function+"!");
    }


    public double calculate(ExpressionNode expr){
        PolishNotationVisitor visitor = new PolishNotationVisitor();
        expr.accept(visitor);
        return CalculateValue.getValue(visitor.list);
    }

}
