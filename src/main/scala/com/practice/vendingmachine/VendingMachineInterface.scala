package com.practice.vendingmachine

import com.practice.vendingmachine.model.{Coin, Item}

trait VendingMachineInterface {

  /**
    * Select item and return item price
    */
  def selectItemAndReturnPrice(item: Item): Long

  /**
    * Function to insert coin in the machine
    */
  def insertCoin(coin: Coin): Unit

  /**
    * Function to refund the amount
    */
  def refundAmount(): Seq[Coin]

  /**
    * Function to return selected items and change
    */
  def collectItemAndChange(): (Item, Seq[Coin])

  /**
    * Function to reset the machine
    */
  def resetMachine(): Unit
}
