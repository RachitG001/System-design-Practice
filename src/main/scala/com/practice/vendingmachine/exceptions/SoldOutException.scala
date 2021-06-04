package com.practice.vendingmachine.exceptions

class SoldOutException extends VendingMachineException {

  /**
    * Error message to return to user
    *
    * @return
    */
  override val errorMessage: String = "Sold out, please buy another item"
}
