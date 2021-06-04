package com.practice.vendingmachine.exceptions

class NotFullPaidException(remainingBalance: Long)
    extends VendingMachineException {

  /**
    * Error message to return to user
    *
    * @return
    */
  override val errorMessage: String =
    s"Amount not fully paid, remaining balance: $remainingBalance"
}
