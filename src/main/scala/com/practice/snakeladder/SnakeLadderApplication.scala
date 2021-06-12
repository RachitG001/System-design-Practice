package com.practice.snakeladder

object SnakeLadderApplication {

  def main(args: Array[String]): Unit = {

    println("Enter size of board: ")
    val boardSize = scala.io.StdIn.readInt()

    println("Enter no of snakes: ")
    val noOfSnakes = scala.io.StdIn.readInt()

    println("Enter no of ladders: ")
    val noOfLadders = scala.io.StdIn.readInt()

    val game = new Game(boardSize, noOfSnakes, noOfLadders)

    println("Enter player 1 name: ")
    val player1 = scala.io.StdIn.readLine()

    println("Enter player 2 name: ")
    val player2 = scala.io.StdIn.readLine()

    game.addPlayer(player1)
    game.addPlayer(player2)
    game.showBoardDetails()
    game.playGame()
  }
}
