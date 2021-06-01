package com.practice.splitwise.models

case class Expense(
    id: String,
    title: String,
    groupId: String,
    userBalances: BalanceMap,
    isSettled: Boolean
)
