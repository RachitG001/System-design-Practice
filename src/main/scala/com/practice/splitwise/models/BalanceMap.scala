package com.practice.splitwise.models

case class BalanceMap(
    var balances: Map[String, Balance] = Map.empty
) {

  def getUserBalances: Map[String, Balance] = this.balances

  def addBalance(userId: String, balance: Balance): BalanceMap = {
    this.balances += (userId -> balance)
    this
  }
}
