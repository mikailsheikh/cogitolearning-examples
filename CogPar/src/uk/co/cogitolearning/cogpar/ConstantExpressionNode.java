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
 * An ExpressionNode that stores a constant value
 */
public class ConstantExpressionNode implements ExpressionNode
{
  /** The value of the constant */
  private double value;

  /**
   * Construct with the fixed value.
   * 
   * @param value
   *          the value of the constant
   */
  public ConstantExpressionNode(double value)
  {
    this.value = value;
  }

  /**
   * Convenience constructor that takes a string and converts it to a double
   * before storing the value.
   * 
   * @param value
   *          the string representation of the value
   */
  public ConstantExpressionNode(String value)
  {
    this.value = Double.valueOf(value);
  }

  /**
   * Returns the value of the constant
   */
  public double getValue()
  {
    return value;
  }

  /**
   * Returns the type of the node, in this case ExpressionNode.CONSTANT_NODE
   */
  public int getType()
  {
    return ExpressionNode.CONSTANT_NODE;
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
