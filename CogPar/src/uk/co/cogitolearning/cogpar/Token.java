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
 * A token that is produced by Tokenizer and fed into Parser.parse
 * 
 * A token consists of a token identifier, a string that the token was
 * created from and the position in the input string that the token was found.
 * 
 * The token id must be one of a number of pre-defined values
 */
public class Token
{
  /** Token id for the epsilon terminal */
  public static final int EPSILON = 0;
  /** Token id for plus or minus */
  public static final int PLUSMINUS = 1;
  /** Token id for multiplication or division */
  public static final int MULTDIV = 2;
  /** Token id for the exponentiation symbol */
  public static final int RAISED = 3;
  /** Token id for function names */
  public static final int FUNCTION = 4;
  /** Token id for opening brackets */
  public static final int OPEN_BRACKET = 5;
  /** Token id for closing brackets */
  public static final int CLOSE_BRACKET = 6;
  /** Token id for numbers */
  public static final int NUMBER = 7;
  /** Token id for variable names */
  public static final int VARIABLE = 8;

  /** the token identifier */
  public final int token;
  /** the string that the token was created from */
  public final String sequence;
  /** the position of the token in the input string */
  public final int pos;

  /**
   * Construct the token with its values
   * @param token the token identifier
   * @param sequence the string that the token was created from
   * @param pos the position of the token in the input string
   */
  public Token(int token, String sequence, int pos)
  {
    super();
    this.token = token;
    this.sequence = sequence;
    this.pos = pos;
  }

}