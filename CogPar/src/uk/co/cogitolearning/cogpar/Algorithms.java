package uk.co.cogitolearning.cogpar;

import java.util.Iterator;

public class Algorithms {
    static void setVariable(ExpressionNode root, String name, double value) {
        SetVariable piVisitor = new SetVariable(name, value);
        Iterator<ExpressionNode> it = root.iterator();
        while(it.hasNext())
            it.next().acceptOnce(piVisitor);
    }
}
