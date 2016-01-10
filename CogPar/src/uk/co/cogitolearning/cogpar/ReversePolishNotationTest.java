package uk.co.cogitolearning.cogpar;

import org.junit.Assert;
import org.junit.Test;


public class ReversePolishNotationTest {
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


        Assert.assertEquals(20.0, multi.getValue(), 0.1);
        Assert.assertEquals(20.0, CalculateValue.calculate(multi), 0.1);
        Assert.assertEquals(multi.getValue(), CalculateValue.calculate(multi), 0.1);
    }
    
    @Test
    public void extendedTest(){
        ExpressionNode root;


        // String exprstr = "6*(3+sin(pi/2))^5"; //=6144
        ConstantExpressionNode five = new ConstantExpressionNode(6);

        DivExpressionNode halfPi = new DivExpressionNode();
        VariableExpressionNode pi = new VariableExpressionNode("pi");
        halfPi.add(pi, true);
        halfPi.add(new ConstantExpressionNode(2), true);

        FunctionExpressionNode sin = new FunctionExpressionNode(FunctionExpressionNode.SIN, halfPi);
        AdditionExpressionNode braces = new AdditionExpressionNode(new ConstantExpressionNode(3), true);
        braces.add(sin, true);
        ExponentiationExpressionNode exponent = new ExponentiationExpressionNode(braces, new ConstantExpressionNode(5));
        MultiplicationExpressionNode multiplication = new MultiplicationExpressionNode(five, true);
        multiplication.add(exponent, true);

        root = multiplication;

        Algorithms.setVariable(root, "pi", Math.PI);

        Assert.assertEquals(6144.0, CalculateValue.calculate(root), 0.1);
    }

}
