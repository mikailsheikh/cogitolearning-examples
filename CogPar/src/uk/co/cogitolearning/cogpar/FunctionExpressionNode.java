/*
 * This software and all files contained in it are distrubted under the MIT license.
 * 
 * Copyright (c) 2013 Cogito Learning Ltd
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package uk.co.cogitolearning.cogpar;

/**
 * An ExpressionNode that handles mathematical functions.
 * 
 * Some pre-defined functions are handled, others can easily be added.
 */
public class FunctionExpressionNode implements ExpressionNode
{
  /** function id for the sin function */
  public static final int SIN = 1;
  /** function id for the cos function */
  public static final int COS = 2;
  /** function id for the tan function */
  public static final int TAN = 3;

  /** function id for the asin function */
  public static final int ASIN = 4;
  /** function id for the acos function */
  public static final int ACOS = 5;
  /** function id for the atan function */
  public static final int ATAN = 6;

  /** function id for the sqrt function */
  public static final int SQRT = 7;
  /** function id for the exp function */
  public static final int EXP = 8;

  /** function id for the ln function */
  public static final int LN = 9;
  /** function id for the log function */
  public static final int LOG = 10;
  /** function id for the log2 function */
  public static final int LOG2 = 11;

  /** the id of the function to apply to the argument */
  private int function;

  /** the argument of the function */
  private ExpressionNode argument;

  /**
   * Construct a function by id and argument.
   * 
   * @param function
   *          the id of the function to apply
   * @param argument
   *          the argument of the function
   */
  public FunctionExpressionNode(int function, ExpressionNode argument)
  {
    super();
    this.function = function;
    this.argument = argument;
  }

  /**
   * Returns the type of the node, in this case ExpressionNode.FUNCTION_NODE
   */
  public int getType()
  {
    return ExpressionNode.FUNCTION_NODE;
  }

  /**
   * Converts a string to a function id.
   * 
   * If the function is not found this method throws an error.
   * 
   * @param str
   *          the name of the function
   * @return the id of the function
   */
  public static int stringToFunction(String str)
  {
    if (str.equals("sin"))
      return FunctionExpressionNode.SIN;
    if (str.equals("cos"))
      return FunctionExpressionNode.COS;
    if (str.equals("tan"))
      return FunctionExpressionNode.TAN;

    if (str.equals("asin"))
      return FunctionExpressionNode.ASIN;
    if (str.equals("acos"))
      return FunctionExpressionNode.ACOS;
    if (str.equals("atan"))
      return FunctionExpressionNode.ATAN;

    if (str.equals("sqrt"))
      return FunctionExpressionNode.SQRT;
    if (str.equals("exp"))
      return FunctionExpressionNode.EXP;

    if (str.equals("ln"))
      return FunctionExpressionNode.LN;
    if (str.equals("log"))
      return FunctionExpressionNode.LOG;
    if (str.equals("log2"))
      return FunctionExpressionNode.LOG2;

    throw new ParserException("Unexpected Function " + str + " found");
  }

  /**
   * Returns a string with all the function names concatenated by a | symbol.
   * 
   * This string is used in Tokenizer.createExpressionTokenizer to create a
   * regular expression for recognizing function names.
   * 
   * @return a string containing all the function names
   */
  public static String getAllFunctions()
  {
    return "sin|cos|tan|asin|acos|atan|sqrt|exp|ln|log|log2";
  }

  /**
   * Returns the value of the sub-expression that is rooted at this node.
   * 
   * The argument is evaluated and then the function is applied to the resulting
   * value.
   */
  public double getValue()
  {
    switch (function)
    {
      case SIN:
        return Math.sin(argument.getValue());
      case COS:
        return Math.cos(argument.getValue());
      case TAN:
        return Math.tan(argument.getValue());
      case ASIN:
        return Math.asin(argument.getValue());
      case ACOS:
        return Math.acos(argument.getValue());
      case ATAN:
        return Math.atan(argument.getValue());
      case SQRT:
        return Math.sqrt(argument.getValue());
      case EXP:
        return Math.exp(argument.getValue());
      case LN:
        return Math.log(argument.getValue());
      case LOG:
        return Math.log(argument.getValue()) * 0.43429448190325182765;
      case LOG2:
        return Math.log(argument.getValue()) * 1.442695040888963407360;
    }

    throw new EvaluationException("Invalid function id "+function+"!");
  }

  /**
   * Implementation of the visitor design pattern.
   * 
   * Calls visit on the visitor and then passes the visitor on to the accept
   * method of the argument.
   * 
   * @param visitor
   *          the visitor
   */
  public void accept(ExpressionNodeVisitor visitor)
  {
    visitor.visit(this);
    argument.accept(visitor);
  }

}
