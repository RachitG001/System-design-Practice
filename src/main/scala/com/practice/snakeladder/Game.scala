package com.practice.snakeladder

import com.practice.snakeladder.models.{Board, Dice, Player}

import scala.collection.mutable
import scala.util.control.Breaks.{break, breakable}

class Game(
    players: mutable.Queue[Player],
    board: Board,
    dice: Dice
) {

  /**
    * Constructor method to initialize the game
    * @param boardSize
    * @param noOfSnakes
    * @param noOfLadders
    */

  def this(boardSize: Int, noOfSnakes: Int, noOfLadders: Int) = {
    this(
      players = mutable.Queue.empty,
      board = Board(boardSize, noOfSnakes, noOfLadders),
      dice = new Dice
    )
  }

  /**
    * Method to add player to the game
    * @param playerName
    */
  def addPlayer(playerName: String): Unit =
    this.players.append(Player(playerName))

  def playGame(): Unit = {
    breakable {
      while (true) {
        if (this.players.size < 2) {
          println(
            "Not enough player to continue the game, min requirement: 2 players"
          )
          break()
        }
        val currentPlayer = this.players.dequeue()
        val stepsToMove = rollDice()
        val absPos =
          board.getAbsolutePos(currentPlayer.currentPos + stepsToMove)
        if (absPos > board.getEndPoint) {
          println(
            s"Not a valid move for player ${currentPlayer.playerName}, need ${this.board.getEndPoint - currentPlayer.currentPos} to win"
          )
          this.players.append(currentPlayer)
        } else {
          currentPlayer.updatePosition(absPos)
          if (absPos == board.getEndPoint) {
            println(s"Player ${currentPlayer.playerName} won")
            currentPlayer.setWinner()
          } else {
            println(
              s"Player ${currentPlayer.playerName} new position is ${absPos}"
            )
            this.players.append(currentPlayer)
          }
        }
      }
    }
  }

  /**
    * Function to roll dice
    */
  private def rollDice(): Int = this.dice.roll()

  /**
    * Function to display board configuration
    */
  def showBoardDetails(): Unit = println(this.board)
}
