package com.practice

import com.practice.cache.Cache
import com.practice.passwordChecker._
import com.practice.rideBookingApp.ride.Address
import com.practice.rideBookingApp.rideBookingApplication.RideBookingApplication
import com.practice.rideBookingApp.user.Rider

object Main {

  def main(args: Array[String]): Unit = {

//    val cache = new Cache[String, String](5)
//    cache.put("1", "One")
//    cache.put("2", "Two")
//    cache.put("3", "Three")
//    println(cache.get("2"))
//    println(cache.get("4"))
//    cache.put("4", "Four")
//    cache.put("5", "Five")
//    cache.put("6", "Six")
//    println(cache.get("4"))
//    println(cache.get("6"))
//

//    val passwordChecker = PasswordChecker.default()
//
//    val xx: PasswordStrength = passwordChecker.measureStrength("randomg")
//    println(xx.displayValue)

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
