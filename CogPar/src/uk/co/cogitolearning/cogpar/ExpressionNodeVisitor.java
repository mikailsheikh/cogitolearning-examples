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
 * An interface for the visitor design pattern.
 * 
 * Expression nodes can be visited by ExpressionNodeVisitor by calling their
 * accept methods. The expression nodes, in turn, call the appropriate visit
 * method of the expression node visitor.
 */
public interface ExpressionNodeVisitor
{
  /** Visit a VariableExpressionNode */
  public void visit(VariableExpressionNode node);

  /**  Visit a ConstantExpressionNode */
  public void visit(ConstantExpressionNode node);

  /**  Visit a AdditionExpressionNode */
  public void visit(AdditionExpressionNode node);

  /**  Visit a MultiplicationExpressionNode */
  public void visit(MultiplicationExpressionNode node);

  /**  Visit a ExponentiationExpressionNode */
  public void visit(ExponentiationExpressionNode node);

  /**  Visit a FunctionExpressionNode */
  public void visit(FunctionExpressionNode node);
}
