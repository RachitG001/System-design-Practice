package com.practice.snakeladder.models

import scala.util.Random
import scala.util.control.Breaks.{break, breakable}

case class Snake(
    head: Int,
    tail: Int
)

case class Ladder(
    start: Int,
    end: Int
)

class Board(
    size: Int,
    startPoint: Int,
    endPoint: Int,
    snakes: Seq[Snake],
    ladders: Seq[Ladder]
) {

  override def toString: String =
    s"""Board size: ${this.size}
      |No of snakes: ${this.snakes.size}
      |Snakes are at pos: ${this.snakes.map(s => (s.head, s.tail))}
      |No of ladders: ${this.ladders.size}
      |Ladders are at pos: ${this.ladders.map(l => (l.start, l.end))}
      |""".stripMargin

  def getStartPoint: Int = this.startPoint
  def getEndPoint: Int = this.endPoint

  def getAbsolutePos(pos: Int): Int = {
    var finalPos = pos
    breakable {
      while (true) {
        val snakeCheck = snakes.find(_.head == finalPos)
        val ladderCheck = ladders.find(_.start == finalPos)
        if (snakeCheck.isEmpty && ladderCheck.isEmpty) {
          break()
        } else if (snakeCheck.isDefined) {
          finalPos = snakeCheck.get.tail
        } else {
          finalPos = ladderCheck.get.end
        }
      }
    }
    finalPos
  }
}

object Board {
  def apply(boardSize: Int, noOfSnakes: Int, noOfLadders: Int): Board = {
    val startPoint = 1
    val endPoint = startPoint + boardSize - 1

    val snakes = 1.to(noOfSnakes).map { _ =>
      val head = Random.between(startPoint + noOfSnakes, endPoint)
      val tail = Random.between(startPoint, head)
      Snake(head, tail)
    }

    val ladders = 1.to(noOfLadders).map { _ =>
      val start = Random.between(startPoint + noOfSnakes, endPoint)
      val end = Random.between(startPoint + noOfSnakes, endPoint)
      Ladder(start, end)
    }
    new Board(boardSize, startPoint, endPoint, snakes, ladders)
  }
}
