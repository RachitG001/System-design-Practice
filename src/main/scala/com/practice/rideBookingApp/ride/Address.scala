package com.practice.rideBookingApp.ride

import scala.util.Random

case class Address(
    city: String,
    state: String
) {
  override def toString: String = s"$city, $state"
}

object Address {
  /*
      Using a random function to calculate distance
   */
  def calculateDistance(origin: Address, destination: Address): Int =
    Random.between(10, 100)
}
