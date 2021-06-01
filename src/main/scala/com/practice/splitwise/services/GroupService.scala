package com.practice.splitwise.services

import com.practice.splitwise.models._

class GroupService(
    private final val expenseService: ExpenseService,
    private final var groups: Map[String, Group]
) {

  def calculatePaymentGraph(groupId: String, userId: String): PaymentGraph = {

    val resultantExpense = getGroupBalances(groupId, userId)
    this.expenseService.getPaymentGraph(resultantExpense)
  }

  def getGroupBalances(groupId: String, userId: String): BalanceMap = {
    if (this.groups.get(groupId).exists(_.userIds.contains(userId))) {
      val groupExpenses = this.expenseService.getGroupExpenses(groupId)
      sumExpenses(groupExpenses)
    } else {
      throw new IllegalAccessException()
    }
  }

  private def sumExpenses(expenses: Seq[Expense]): BalanceMap = {
    val userExpenseMap: Map[String, Balance] =
      expenses.foldLeft(Map[String, Balance]()) {
        case (result, expense) =>
          expense.userBalances.balances.foldLeft(result) {
            case (result, (userId, balance)) =>
              val existingBalance = result.get(userId)
              result + (userId -> balance.addBalance(existingBalance))
          }
      }
    BalanceMap(userExpenseMap)
  }

}
