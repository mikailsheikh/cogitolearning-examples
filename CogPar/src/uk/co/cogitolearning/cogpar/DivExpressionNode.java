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
 * An ExpressionNode that handles divisions. The node can hold
 * an two number of factors that are divided.
 * 
 */
public class DivExpressionNode extends SequenceExpressionNode
{
  /**
   * Default constructor.
   */
  public DivExpressionNode()
  {}

  /**
   * Constructor to create a multiplication with the first term already added.
   *
   * @param a
   *          the term to be added
   * @param positive
   *          a flag indicating whether the term is multiplied or divided
   */
  public DivExpressionNode(ExpressionNode a, boolean positive)
  {
    super(a, positive);
  }

  /**
   * Returns the type of the node, in this case ExpressionNode.MULTIPLICATION_NODE
   */
  public int getType()
  {
    return ExpressionNode.DIVISION_NODE;
  }

  public double getValue()
  {
    // Not used
    return 0;
  }

  /**
   * Implementation of the visitor design pattern.
   * 
   * Calls visit on the visitor and then passes the visitor on to the accept
   * method of all the terms in the product.
   * 
   * @param visitor
   *          the visitor
   */
  public void accept(ExpressionNodeVisitor visitor)
  {
    visitor.visit(this);  
    for (Term t: terms)
      t.expression.accept(visitor);
  }

  @Override
  public void acceptOnce(ExpressionNodeVisitor visitor) {
    visitor.visit(this);
  }

}
