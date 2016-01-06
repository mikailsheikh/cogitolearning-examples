package uk.co.cogitolearning.cogpar;


import java.util.Stack;

public class CalculateVisitor implements ExpressionNodeVisitor{
    private Stack<Double> stack;

    public CalculateVisitor(Stack<Double> stack) {

        this.stack = stack;
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
        stack.push(CalculateValue.functionGetValue(node.getFunction(), operand1));
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
