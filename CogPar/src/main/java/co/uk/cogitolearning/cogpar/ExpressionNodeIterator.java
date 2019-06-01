package co.uk.cogitolearning.cogpar;

import java.util.ArrayList;
import java.util.Iterator;

public class ExpressionNodeIterator implements Iterator<ExpressionNode> {
    private final ArrayList<ExpressionNode> list = new ArrayList<ExpressionNode>();

    public ExpressionNodeIterator(ExpressionNode expressionNode) {
        IteratorVisitor visitor = new IteratorVisitor(list);
        expressionNode.acceptOnce(visitor);
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public ExpressionNode next() {
        return list.remove(0);
    }

    private class IteratorVisitor implements ExpressionNodeVisitor {
        private ArrayList<ExpressionNode> list;

        public IteratorVisitor(ArrayList<ExpressionNode> stack) {
            this.list = stack;
        }

        public void visit(VariableExpressionNode node) {
            list.add(node);
        }

        public void visit(ConstantExpressionNode node) {
            list.add(node);
        }

        public void visit(AdditionExpressionNode node) {
            list.add(node);
            for (SequenceExpressionNode.Term t : node.terms)
                t.expression.acceptOnce(this);
        }

        public void visit(MultiplicationExpressionNode node) {
            list.add(node);
            for (SequenceExpressionNode.Term t : node.terms)
                t.expression.acceptOnce(this);
        }

        public void visit(ExponentiationExpressionNode node) {
            list.add(node);
            node.getBase().acceptOnce(this);
            node.getExponent().acceptOnce(this);
        }

        public void visit(FunctionExpressionNode node) {
            list.add(node);
            node.getArgument().acceptOnce(this);
        }

        public void visit(DivExpressionNode node) {
            list.add(node);
            for (SequenceExpressionNode.Term t : node.terms)
                t.expression.acceptOnce(this);

        }
    }
}
