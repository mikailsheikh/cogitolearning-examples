package co.uk.cogitolearning.cogpar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class CalculateValue {

    public CalculateValue() {
    }

    private static double calculatePolishNotation(ArrayList<ExpressionNode> list) {
        // https://en.wikipedia.org/wiki/Polish_notation
        Collections.reverse(list); // Scan the given prefix expression from right to left
        Stack<Double> stack = new Stack<Double>();
        CalculateVisitor calculateVisitor = new CalculateVisitor(stack);

        for(ExpressionNode node: list)
            node.acceptOnce(calculateVisitor);

        return stack.pop();
    }


    static public double calculate(ExpressionNode expr){
        ArrayList<ExpressionNode> polishNotationList = new ArrayList<ExpressionNode>();

        for (ExpressionNode node : (Iterable<ExpressionNode>) expr)
            polishNotationList.add(node);

        return CalculateValue.calculatePolishNotation(polishNotationList);
    }

}
