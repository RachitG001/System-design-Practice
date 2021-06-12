package com.practice.snakeladder.models

case class Player(
    playerName: String,
    var currentPos: Int = 0,
    var isWinner: Boolean = false
) {

  def updatePosition(pos: Int): Unit = this.currentPos = pos

  def setWinner(): Unit = this.isWinner = true
}
