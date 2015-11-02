package uk.co.cogitolearning.cogpar;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class ReversePolishNotation {
    @Test
    public void test1(){
        // (2+3)*4
        ConstantExpressionNode two = new ConstantExpressionNode(2);
        ConstantExpressionNode three = new ConstantExpressionNode(3);
        ConstantExpressionNode four = new ConstantExpressionNode(4);

        SequenceExpressionNode sum = new AdditionExpressionNode(two, true);
        sum.add(three, true);
        SequenceExpressionNode multi = new MultiplicationExpressionNode(sum, true);
        multi.add(four, true);
        
        
        SequenceExpressionNode root = multi;
        Assert.assertEquals(20.0, root.getValue(), 0.1);

        PolishNotationVisitor visitor = new PolishNotationVisitor();

        root.accept(visitor);
        System.out.println("The value of the visitation is " + visitor.list);

        // * + 2 3 4
        Assert.assertEquals(20.0, getValue(visitor.list), 0.1);

    }

    private double getValue(ArrayList<ExpressionNode> list) {
        // https://en.wikipedia.org/wiki/Polish_notation
        Collections.reverse(list); // Scan the given prefix expression from right to left
        Stack<ExpressionNode> stack = new Stack<ExpressionNode>();
        
        for(ExpressionNode node: list){
            if (node instanceof MultiplicationExpressionNode){
                ExpressionNode operand1 = stack.pop();
                ExpressionNode operand2 = stack.pop();
                double compute = operand1.getValue() * operand2.getValue();
                stack.push(new ConstantExpressionNode(compute));
            }
            if (node instanceof ConstantExpressionNode){
                stack.push(node);
            }
            if (node instanceof AdditionExpressionNode){
                ExpressionNode operand1 = stack.pop();
                ExpressionNode operand2 = stack.pop();
                double compute = operand1.getValue() + operand2.getValue();
                stack.push(new ConstantExpressionNode(compute));
            }

        }
        
        return stack.pop().getValue();
    }

    private class PolishNotationVisitor implements ExpressionNodeVisitor{
        public ArrayList<ExpressionNode> list = new ArrayList<ExpressionNode>();
        
        public void visit(VariableExpressionNode node){list.add(node);};

        public void visit(ConstantExpressionNode node){
            list.add(node);
        };

        public void visit(AdditionExpressionNode node){list.add(node);};

        public void visit(MultiplicationExpressionNode node){list.add(node);};

        public void visit(ExponentiationExpressionNode node){list.add(node);};

        public void visit(FunctionExpressionNode node){list.add(node);};

    }
}
