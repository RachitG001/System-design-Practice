package com.practice.rideSharingApp

import scala.util.Random

case class Address(
    city: String,
    state: String
)

object Address {
  /*
      Using a random function to calculate distance
   */
  def calculateDistance(startingPoint: Address, endingPoint: Address): Int =
    Random.between(10, 100)
}
