/* 
 * BadTransactionException.java
 * Alicia Sheng
 * 6/7/2020
 */

/**
 *  Implements an exception that should be thrown for invalid transactions.
 **/
public class BadTransactionException extends Exception {

  public int transactionAmount;  // The invalid account number.

  /**
   *  Creates an exception object for nonexistent account "badAcctNumber".
   **/
  public BadTransactionException(int badTransactionAmount) {
    super("Invalid transaction amount: " + badTransactionAmount);
    
    transactionAmount = badTransactionAmount;
  }
}