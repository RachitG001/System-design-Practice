package com.practice.splitwise.models

case class Balance(
    currency: String = "USD",
    var amount: Double
) {

  def addBalance(balance: Option[Balance]): Balance =
    Balance(this.currency, this.amount + balance.map(_.amount).getOrElse(0.0))

  def subtractBalance(balance: Balance): Balance = {
    Balance(this.currency, this.amount - balance.amount)
  }
}
