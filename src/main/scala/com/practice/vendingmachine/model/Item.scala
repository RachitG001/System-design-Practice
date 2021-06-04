package com.practice.vendingmachine.model

sealed abstract class Item(
    itemName: String,
    itemPrice: Long
) {

  /**
    * @return name of the item
    */
  def getItemName: String = this.itemName

  /**
    * @return price of the item
    */

  def getItemPrice: Long = this.itemPrice
}

object Item {

  object Coke extends Item(itemName = "Coke", itemPrice = 20)

  object Pepsi extends Item(itemName = "Coke", itemPrice = 25)

  object Soda extends Item(itemName = "Soda", itemPrice = 30)

  def allItems: Set[Item] = Set(Coke, Pepsi, Soda)

}
