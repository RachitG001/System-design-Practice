package com.practice.rideBookingApp.ride

import java.time.LocalDateTime

case class Ride(
    id: Int,
    userId: Int,
    driverId: Int,
    origin: Address,
    destination: Address,
    noOfSeats: Int
) {
  var isActive = true
  val startTime: LocalDateTime = LocalDateTime.now()
  var endTime: Option[LocalDateTime] = None
  var rideAmount: Option[Double] = None

  override def toString: String =
    s"""(UserId : $userId
      |DriverId: $driverId
      |Origin: $origin
      |Destination: $destination
      |No of seats: $noOfSeats)
      |""".stripMargin

  def finishRide(rideAmount: Double): Unit = {
    this.isActive = false
    this.endTime = Some(LocalDateTime.now())
    this.rideAmount = Some(rideAmount)
  }
}
