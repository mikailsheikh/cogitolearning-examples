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
 * An ExpressionNode that stores a named variable
 */
public class VariableExpressionNode implements ExpressionNode
{
  /** The name of the variable */
  private String name;
  /** The value of the variable */
  private double value;
  /** indicates if the value has been set */
  private boolean valueSet;

  /**
   * Construct with the name of the variable.
   * 
   * @param name
   *          the name of the variable
   */
  public VariableExpressionNode(String name)
  {
    this.name = name;
    valueSet = false;
  }

  /**
   * @return the name of the variable
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns the type of the node, in this case ExpressionNode.VARIABLE_NODE
   */
  public int getType()
  {
    return ExpressionNode.VARIABLE_NODE;
  }

  /**
   * Sets the value of the variable
   * 
   * @param value
   *          the value of the variable
   */
  public void setValue(double value)
  {
    this.value = value;
    this.valueSet = true;
  }

  /**
   * Returns the value of the variable but throws an exception if the value has
   * not been set
   */
  public double getValue()
  {
    if (valueSet)
      return value;
    else
      throw new EvaluationException("Variable '" + name + "' was not initialized.");
  }

  /**
   * Implementation of the visitor design pattern.
   * 
   * Calls visit on the visitor.
   * 
   * @param visitor
   *          the visitor
   */
  public void accept(ExpressionNodeVisitor visitor)
  {
    visitor.visit(this);
  }

}
