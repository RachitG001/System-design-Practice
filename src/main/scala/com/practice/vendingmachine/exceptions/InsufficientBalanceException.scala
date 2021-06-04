package com.practice.vendingmachine.exceptions

class InsufficientBalanceException extends VendingMachineException {

  /**
    * Error message to return to user
    *
    * @return
    */
  override val errorMessage: String =
    "Not sufficient change available, pls buy another product"
}
