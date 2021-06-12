package com.practice.snakeladder.models

import scala.util.Random

class Dice(
    minValue: Int = 1,
    maxValue: Int = 6
) {
  def roll(): Int = {
    Random.between(minValue, maxValue + 1)
  }
}
