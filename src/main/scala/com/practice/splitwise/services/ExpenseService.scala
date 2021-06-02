package com.practice.splitwise.services

import com.practice.splitwise.models._

import scala.collection.mutable

class ExpenseService(
    private final var groupExpenses: Map[String, Seq[Expense]] = Map.empty
) {

  /**
    * Get method to fetch all expenses for a group
    */
  def getGroupExpenses(groupId: String): Seq[Expense] = {
    this.groupExpenses.getOrElse(groupId, Seq.empty)
  }

  /**
    * Method to add expenses
    */
  def addExpense(expense: Expense): Unit = {
    val groupId = expense.groupId
    this.groupExpenses = this.groupExpenses + (groupId -> (this.groupExpenses
      .getOrElse(groupId, Seq.empty) :+ expense))
  }

  /**
    * Method to find payment graph for an expense
    */
  def getPaymentGraph(balances: BalanceMap): PaymentGraph = {
    var paymentGraph: Map[String, BalanceMap] = Map.empty

    val negative: mutable.PriorityQueue[(String, Balance)] =
      mutable.PriorityQueue.empty(
        Ordering.by[(String, Balance), Double](_._2.amount)
      )
    val positive: mutable.PriorityQueue[(String, Balance)] =
      mutable.PriorityQueue.empty(
        Ordering.by[(String, Balance), Double](_._2.amount)
      )
    balances.balances.foreach {
      case (userId, balance) =>
        if (balance.amount > 0) {
          positive.addOne((userId, balance))
        } else {
          negative.addOne((userId, balance))
        }
    }
    while (negative.nonEmpty && positive.nonEmpty) {
      val (payer, balanceToPay) = negative.dequeue()
      val (payee, balanceToReceive) = positive.dequeue()
      val amountToPay = -balanceToPay.amount
      val amountToReceive = balanceToReceive.amount
      val amountToBalance = Math.min(amountToPay, amountToReceive)
      paymentGraph = paymentGraph + (payee -> paymentGraph
        .getOrElse(payee, BalanceMap())
        .addBalance(payer, Balance(amount = amountToBalance)))
      val remaining = amountToReceive - amountToPay
      if (remaining > 0) {
        positive.addOne((payee, Balance(amount = remaining)))
      } else {
        negative.addOne((payer, Balance(amount = remaining)))
      }
    }
    PaymentGraph(paymentGraph)
  }

}
