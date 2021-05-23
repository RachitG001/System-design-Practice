package com.practice.rideSharingApp

import java.time.LocalDateTime

case class Ride(
    id: Int,
    userId: Int,
    driverId: Int,
    startingPoint: Address,
    endingPoint: Address,
    noOfSeats: Int
) {
  var isActive = false
  val startTime: LocalDateTime = LocalDateTime.now()
  var endTime: Option[LocalDateTime] = None
  var rideAmount: Option[Double] = None

  def finishRide(rideAmount: Double): Unit = {
    this.isActive = false
    this.endTime = Some(LocalDateTime.now())
    this.rideAmount = Some(rideAmount)
  }
}
