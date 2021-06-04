package com.practice.vendingmachine.model

sealed abstract case class Coin(denomination: Long)

object Coin {
  object Penny extends Coin(1)
  object Nickle extends Coin(5)
  object Dime extends Coin(10)
  object Quarter extends Coin(25)

  def allCoin: Set[Coin] = Set(Penny, Nickle, Dime, Quarter)

}
