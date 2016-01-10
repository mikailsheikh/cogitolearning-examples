package uk.co.cogitolearning.cogpar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;


public class CalculateValue {

    public CalculateValue() {
    }

    static double getValue(ArrayList<ExpressionNode> list) {
        // https://en.wikipedia.org/wiki/Polish_notation
        Collections.reverse(list); // Scan the given prefix expression from right to left
        Stack<Double> stack = new Stack<Double>();
        CalculateVisitor calculateVisitor = new CalculateVisitor(stack);

        for(ExpressionNode node: list)
            node.acceptOnce(calculateVisitor);

        return stack.pop();
    }


    public double calculate(ExpressionNode expr){
//        PolishNotationVisitor visitor = new PolishNotationVisitor();
//        expr.accept(visitor);
        ArrayList<ExpressionNode> polishNotationList = new ArrayList<ExpressionNode>();

        Iterator<ExpressionNode> it = expr.iterator();
        while(it.hasNext())
            polishNotationList.add(it.next());

        return CalculateValue.getValue(polishNotationList);
    }

}
