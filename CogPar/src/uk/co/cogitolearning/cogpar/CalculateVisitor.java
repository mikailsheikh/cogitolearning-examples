package uk.co.cogitolearning.cogpar;


import java.util.Stack;

import static uk.co.cogitolearning.cogpar.FunctionExpressionNode.*;

public class CalculateVisitor implements ExpressionNodeVisitor{
    private Stack<Double> stack;

    public CalculateVisitor(Stack<Double> stack) {

        this.stack = stack;
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

    public void visit(VariableExpressionNode node) {
        stack.push(node.getValue());
    }

    public void visit(ExponentiationExpressionNode node) {
        double base = stack.pop();
        double exponent = stack.pop();
        stack.push(Math.pow(base, exponent));
    }

    public void visit(FunctionExpressionNode node) {
        double operand1 = stack.pop();
        stack.push(functionGetValue(node.getFunction(), operand1));
    }

    public void visit(AdditionExpressionNode node) {
        double operand1 = stack.pop();
        double operand2 = stack.pop();

        stack.push(operand1 + operand2);
    }

    public void visit(ConstantExpressionNode node) {
        stack.push(node.getValue());
    }

    public void visit(DivExpressionNode node) {
        double operand1 = stack.pop();
        double operand2 = stack.pop();
        stack.push(operand1 / operand2);
    }

    public void visit(MultiplicationExpressionNode node) {
        double operand1 = stack.pop();
        double operand2 = stack.pop();
        stack.push(operand1 * operand2);
    }
}
