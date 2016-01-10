package uk.co.cogitolearning.cogpar;

import java.util.Iterator;
import java.util.Stack;

public class ExpressionNodeIterator implements Iterator<ExpressionNode> {
    private final Stack<ExpressionNode> stack;

    public ExpressionNodeIterator(ExpressionNode expressionNode) {
        stack = new Stack<ExpressionNode>();
        IteratorVisitor visitor = new IteratorVisitor(stack);
        expressionNode.acceptOnce(visitor);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public ExpressionNode next() {
        return stack.pop();
    }

    private class IteratorVisitor implements ExpressionNodeVisitor {
        private Stack<ExpressionNode> stack;

        public IteratorVisitor(Stack<ExpressionNode> stack) {
            this.stack = stack;
        }

        public void visit(VariableExpressionNode node) {
            stack.push(node);
        }

        public void visit(ConstantExpressionNode node) {
            stack.push(node);
        }

        public void visit(AdditionExpressionNode node) {
            stack.push(node);
            for (SequenceExpressionNode.Term t : node.terms)
                t.expression.acceptOnce(this);
        }

        public void visit(MultiplicationExpressionNode node) {
            stack.push(node);
            for (SequenceExpressionNode.Term t : node.terms)
                t.expression.acceptOnce(this);
        }

        public void visit(ExponentiationExpressionNode node) {
            stack.push(node);
            node.getBase().acceptOnce(this);
            node.getExponent().acceptOnce(this);
        }

        public void visit(FunctionExpressionNode node) {
            stack.push(node);
            node.getArgument().acceptOnce(this);
        }

        public void visit(DivExpressionNode node) {
            stack.push(node);
            for (SequenceExpressionNode.Term t : node.terms)
                t.expression.acceptOnce(this);

        }
    }
}
