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
 * An ExpressionNode that handles exponentiation. The node holds
 * a base and an exponent and calulates base^exponent 
 * 
 */
public class ExponentiationExpressionNode implements ExpressionNode
{
  /** the node containing the base */
  private ExpressionNode base;
  /** the node containing the exponent */
  private ExpressionNode exponent;

  /**
   * Construct the ExponentiationExpressionNode with base and exponent
   * @param base the node containing the base
   * @param exponent the node containing the exponent
   */
  public ExponentiationExpressionNode(ExpressionNode base, ExpressionNode exponent)
  {
    this.base = base;
    this.exponent = exponent;
  }

  /**
   * Returns the type of the node, in this case ExpressionNode.EXPONENTIATION_NODE
   */
  public int getType()
  {
    return ExpressionNode.EXPONENTIATION_NODE;
  }
  
  /**
   * Returns the value of the sub-expression that is rooted at this node.
   * 
   * Calculates base^exponent
   */
  public double getValue()
  {
    return Math.pow(base.getValue(), exponent.getValue());
  }

  /**
   * Implementation of the visitor design pattern.
   * 
   * Calls visit on the visitor and then passes the visitor on to the accept
   * method of the base and the exponent.
   * 
   * @param visitor
   *          the visitor
   */
  public void accept(ExpressionNodeVisitor visitor)
  {
    visitor.visit(this);
    base.accept(visitor);
    exponent.accept(visitor);
  }
}
