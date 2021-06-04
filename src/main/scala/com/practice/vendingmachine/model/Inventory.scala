package com.practice.vendingmachine.model

class Inventory[T] {

  private var inventory: Map[T, Int] = Map.empty

  def getQuantity(key: T): Int = this.inventory.getOrElse(key, 0)

  def hasItem(key: T): Boolean = this.getQuantity(key) > 0

  def add(key: T): Unit =
    this.inventory =
      this.inventory.updated(key, this.inventory.getOrElse(key, 0) + 1)

  def deduct(key: T): Unit =
    if (hasItem(key)) {
      this.inventory =
        this.inventory.updated(key, this.inventory.getOrElse(key, 0) - 1)
    }

  def clear(): Unit = this.inventory = Map.empty

  def put(key: T, quantity: Int): Unit =
    this.inventory = this.inventory.updated(key, quantity)
}
