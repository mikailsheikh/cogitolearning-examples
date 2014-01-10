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
 * A visitor that sets a variable with a specific name to a given value
 */
public class SetVariable implements ExpressionNodeVisitor
{

  private String name;
  private double value;

  /**
   * Construct the visitor with the name and the value of the variable to set
   * 
   * @param name
   *          the name of the variable
   * @param value
   *          the value of the variable
   */
  public SetVariable(String name, double value)
  {
    super();
    this.name = name;
    this.value = value;
  }

  /**
   * Checks the nodes name against the name to set and sets the value if the two
   * strings match
   */
  public void visit(VariableExpressionNode node)
  {
    if (node.getName().equals(name))
      node.setValue(value);
  }

  /** Do nothing */
  public void visit(ConstantExpressionNode node)
  {}

  /** Do nothing */
  public void visit(AdditionExpressionNode node)
  {}

  /** Do nothing */
  public void visit(MultiplicationExpressionNode node)
  {}

  /** Do nothing */
  public void visit(ExponentiationExpressionNode node)
  {}

  /** Do nothing */
  public void visit(FunctionExpressionNode node)
  {}

}
