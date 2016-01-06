package uk.co.cogitolearning.cogpar;

import java.util.ArrayList;

class PolishNotationVisitor implements ExpressionNodeVisitor{
    public ArrayList<ExpressionNode> list = new ArrayList<ExpressionNode>();

    public void visit(VariableExpressionNode node){list.add(node);}

    public void visit(ConstantExpressionNode node){
        list.add(node);
    }

    public void visit(AdditionExpressionNode node){list.add(node);}

    public void visit(MultiplicationExpressionNode node){list.add(node);}

    public void visit(ExponentiationExpressionNode node){list.add(node);}

    public void visit(FunctionExpressionNode node){list.add(node);}

    public void visit(DivExpressionNode node){
        list.add(node);
    }

}
