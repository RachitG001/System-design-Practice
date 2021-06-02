package com.practice.splitwise.models

case class PaymentGraph(
    graph: Map[String, BalanceMap]
) {

  def getFinalBalance: Map[(String, String), Double] = {
    var finalBalanceData: Map[(String, String), Double] = Map.empty
    this.graph.foreach {
      case (payee, balanceMap) =>
        balanceMap.balances.foreach {
          case (payer, balance) =>
            finalBalanceData =
              finalBalanceData + ((payee, payer) -> balance.amount)
        }
    }
    finalBalanceData
  }
}
