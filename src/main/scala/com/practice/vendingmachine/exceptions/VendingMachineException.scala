package com.practice.vendingmachine.exceptions

trait VendingMachineException extends Throwable {

  /**
    * Error message to return to user
    *
    * @return
    */
  val errorMessage: String

  override def getMessage: String = this.errorMessage
}
