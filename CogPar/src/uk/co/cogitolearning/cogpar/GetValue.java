package uk.co.cogitolearning.cogpar;

public class GetValue {

    public GetValue() {
    }

    public double run(ExpressionNode expr){
        GetVariableVisitor visitor = new GetVariableVisitor();
        expr.accept(visitor);
                
        return visitor.value();
    }

    public class GetVariableVisitor implements ExpressionNodeVisitor
    {
        public void visit(VariableExpressionNode node){};

        public void visit(ConstantExpressionNode node){};

        public void visit(AdditionExpressionNode node){};

        public void visit(MultiplicationExpressionNode node){
//            double prod = 1.0;
//                if (t.positive)
//                    prod *= t.expression.getValue();
//                else
//                    prod /= t.expression.getValue();
//            }
//            return prod;


        };

        public void visit(ExponentiationExpressionNode node){};

        public void visit(FunctionExpressionNode node){};

        public double value() {
            return 0;
        }
    }

}
