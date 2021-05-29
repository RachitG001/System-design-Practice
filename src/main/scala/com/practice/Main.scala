package com.practice

import com.practice.rideBookingApp.ride.Address
import com.practice.rideBookingApp.rideBookingApplication.RideBookingApplication

object Main {

  def main(args: Array[String]): Unit = {

    val rideSharingApplication = new RideBookingApplication

    rideSharingApplication.addDriver("ironman")
    rideSharingApplication.addDriver("superman")
    println(rideSharingApplication.activeDrivers)
    println(rideSharingApplication.getAvailableDrivers.map(_.toString))

    val origin = Address("Haridwar", "Uk")
    val destination = Address("Noida", "Up")

    val rider1 = rideSharingApplication.addRider("Rachit")
    val rideForRider1 =
      rideSharingApplication.requestRide(
        rider1.id,
        origin,
        destination,
        noOfSeats = 3
      )
    println(rideForRider1)

    val amount = rideSharingApplication.completeRide(rideForRider1.id)
    println(amount)
  }

}
