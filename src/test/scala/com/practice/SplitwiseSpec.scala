package com.practice

import com.practice.splitwise.models.{Balance, BalanceMap, Expense, Group}
import com.practice.splitwise.services.{ExpenseService, GroupService}
import org.scalatest.flatspec.AnyFlatSpec

class SplitwiseSpec extends AnyFlatSpec {

  val users: Seq[String] = Seq("A", "B", "C")
  var groups: Map[String, Group] = Map(
    "123" -> Group(id = "123", title = "testing group", userIds = users)
  )

  val userBalanceMap1: BalanceMap = BalanceMap()
  userBalanceMap1.addBalance("A", Balance(amount = 10))
  userBalanceMap1.addBalance("B", Balance(amount = 20))
  userBalanceMap1.addBalance("C", Balance(amount = -30))

  val userBalanceMap2: BalanceMap = BalanceMap()
  userBalanceMap2.addBalance("A", Balance(amount = -50))
  userBalanceMap2.addBalance("B", Balance(amount = 10))
  userBalanceMap2.addBalance("C", Balance(amount = 40))

  val userBalanceMap3: BalanceMap = BalanceMap()
  userBalanceMap3.addBalance("A", Balance(amount = 90))
  userBalanceMap3.addBalance("B", Balance(amount = 0))
  userBalanceMap3.addBalance("C", Balance(amount = -90))

  val expenses: Seq[Expense] = Seq(
    Expense(
      id = "1",
      title = "Breakfast",
      groupId = "123",
      userBalances = userBalanceMap1,
      isSettled = false
    ),
    Expense(
      id = "2",
      title = "Lunch",
      groupId = "123",
      userBalances = userBalanceMap2,
      isSettled = false
    ),
    Expense(
      id = "3",
      title = "Dinner",
      groupId = "123",
      userBalances = userBalanceMap3,
      isSettled = false
    )
  )

  val expenseService = new ExpenseService()
  expenses.foreach(expenseService.addExpense)

  val groupService = new GroupService(expenseService, groups)

  "getGroupBalance" should "return correct balance at the end" in {
    val payment: BalanceMap = groupService.getGroupBalances("123", "B")

    println(payment)
    assert(payment.getUserBalances.get("A").exists(_.amount == 50))
    assert(payment.getUserBalances.get("B").exists(_.amount == 30))
    assert(payment.getUserBalances.get("C").exists(_.amount == -80))
  }

  it should "throw exception if an illegal user try to access the user balance" in {
    assertThrows[IllegalAccessException](
      groupService.getGroupBalances("123", "D")
    )
  }

  it should "return payment graph" in {
    val paymentGraph =
      groupService.calculatePaymentGraph(groupId = "123", userId = "B")

    val finalBalanceData = paymentGraph.getFinalBalance
    assert(finalBalanceData.get(("A", "C")).contains(50))
    assert(finalBalanceData.get(("B", "C")).contains(30))
  }

}
