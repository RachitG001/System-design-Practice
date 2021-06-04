package com.practice.vendingmachine.exceptions

class NoItemSelectedException extends VendingMachineException {

  /**
    * Error message to return to user
    *
    * @return
    */
  override val errorMessage: String =
    "Please select an item first before checking out"
}
