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
 * A simple subclass of RuntimeException that indicates errors when trying to
 * parse the input to Parser.
 * 
 * The exception stores the token that caused the error.
 */
public class ParserException extends RuntimeException
{
  private static final long serialVersionUID = -1009747984332258423L;
 
  /** the token that caused the error */
  private Token token = null;

  /**
   * Construct the evaluation exception with a message.
   * @param message the message containing the cause of the exception
   */
  public ParserException(String message)
  {
    super(message);
  }

  /**
   * Construct the evaluation exception with a message and a token.
   * @param message the message containing the cause of the exception
   * @param token the token that caused the exception
   */
  public ParserException(String message, Token token)
  {
    super(message);
    this.token = token;
  }
  
  /**
   * Get the token.
   * @return the token that caused the exception
   */
  public Token getToken()
  {
    return token;
  }
  
  /**
   * Overrides RuntimeException.getMessage to add the token information
   * into the error message.
   * 
   *  @return the error message
   */
  public String getMessage()
  {
    String msg = super.getMessage();
    if (token != null)
    {
      msg = msg.replace("%s", token.sequence);
    }
    return msg;
  }

}
