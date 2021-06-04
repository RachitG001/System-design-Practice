package com.practice.vendingmachine
import com.practice.vendingmachine.exceptions._
import com.practice.vendingmachine.model._

class VendingMachineImpl extends VendingMachineInterface {

  private val itemInventory: Inventory[Item] = new Inventory[Item]
  private val coinInventory: Inventory[Coin] = new Inventory[Coin]
  private var totalSales: Long = 0
  private var currentItem: Option[Item] = None
  private var currentBalance: Long = 0

  def this() {
    this()
    Coin.allCoin.foreach { coin =>
      this.coinInventory.put(coin, 5)
    }
    Item.allItems.foreach { item =>
      this.itemInventory.put(item, 5)
    }
  }

  /**
    * Fetch price for an item
    */
  override def selectItemAndReturnPrice(item: Item): Long = {
    if (this.itemInventory.hasItem(item)) {
      currentItem = Some(item)
      item.getItemPrice
    }
    throw new SoldOutException
  }

  /**
    * Function to insert coin in the machine
    */
  override def insertCoin(coin: Coin): Unit = {
    currentBalance += coin.denomination
    this.coinInventory.add(coin)
  }

  /**
    * Function to return selected items and change
    */
  override def collectItemAndChange(): (Item, Seq[Coin]) = {
    val item = this.collectItem()
    val change = this.getChange(item)
    this.currentItem = None
    this.totalSales += item.getItemPrice
    (item, change)
  }

  /**
    * Function to refund the amount
    */
  override def refundAmount(): Seq[Coin] = {
    val change = this.calculateChange(this.currentBalance)
    this.updateCoinInventory(change)
    this.currentBalance = 0
    this.currentItem = None
    change
  }

  /**
    * Function to reset the machine
    */
  override def resetMachine(): Unit = {
    this.totalSales = 0
    this.currentBalance = 0
    this.currentItem = None
    this.itemInventory.clear()
    this.coinInventory.clear()
  }

  def getTotalSales: Long = this.totalSales

  private def collectItem(): Item = {
    currentItem match {
      case Some(item) =>
        val remainingBalance = getRemainingBalanceToCollectOrPay(item)
        if (remainingBalance >= 0) {
          this.itemInventory.deduct(item)
          item
        } else {
          throw new NotFullPaidException(-remainingBalance)
        }
      case None =>
        throw new NoItemSelectedException
    }
  }

  private def getChange(item: Item): Seq[Coin] = {
    val remainingBalance = this.getRemainingBalanceToCollectOrPay(item)
    if (remainingBalance <= 0) {
      Seq.empty
    } else {
      val change = calculateChange(remainingBalance)
      this.updateCoinInventory(change)
      this.currentBalance = 0
      change
    }
  }

  private def calculateChange(balance: Long): Seq[Coin] = {
    var coinsToReturn: Seq[Coin] = Seq.empty
    var remainingBalance = balance
    while (remainingBalance > 0) {
      if (
        remainingBalance >= Coin.Quarter.denomination &&
        this.coinInventory.hasItem(Coin.Quarter)
      ) {
        remainingBalance -= Coin.Quarter.denomination
        coinsToReturn = coinsToReturn :+ Coin.Quarter
      } else if (
        remainingBalance >= Coin.Dime.denomination &&
        this.coinInventory.hasItem(Coin.Dime)
      ) {
        remainingBalance -= Coin.Dime.denomination
        coinsToReturn = coinsToReturn :+ Coin.Dime
      } else if (
        remainingBalance >= Coin.Nickle.denomination &&
        this.coinInventory.hasItem(Coin.Nickle)
      ) {
        remainingBalance -= Coin.Nickle.denomination
        coinsToReturn = coinsToReturn :+ Coin.Nickle
      } else if (
        remainingBalance >= Coin.Penny.denomination &&
        this.coinInventory.hasItem(Coin.Penny)
      ) {
        remainingBalance -= Coin.Penny.denomination
        coinsToReturn = coinsToReturn :+ Coin.Penny
      } else {
        throw new InsufficientBalanceException
      }
    }
    coinsToReturn
  }

  private def getRemainingBalanceToCollectOrPay(item: Item): Long =
    this.currentBalance - item.getItemPrice

  private def updateCoinInventory(coinsUsed: Seq[Coin]): Unit = {
    coinsUsed.foreach(this.coinInventory.deduct)
  }
}
