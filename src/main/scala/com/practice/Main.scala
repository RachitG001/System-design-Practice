package com.practice

import com.practice.cache.Cache
import com.practice.passwordChecker._
import com.practice.rideSharingApp.{Address, RideSharingApplication}
import com.practice.rideSharingApp.user.Rider

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

    val rideSharingApplication = new RideSharingApplication

    rideSharingApplication.addDriver("ironman")
    rideSharingApplication.addDriver("superman")
//    rideSharingApplication.addDriver("batman")
    println(rideSharingApplication.activeDrivers)
    println(rideSharingApplication.getAvailableDrivers.map(_.toString))

    val a1 = Address("Delhi", "Delhi")
    val a2 = Address("Noida", "Up")

    val rider1 = rideSharingApplication.addRider("Rachit")
    val rideForRider1 = rideSharingApplication.requestRide(rider1.id, a1, a2, 3)

    println(rideForRider1)
    println(rideSharingApplication.getAvailableDrivers.map(_.toString))
    println(rideSharingApplication.getActiveRides.map(_.toString))

    val rider2 = rideSharingApplication.addRider("Lakshya")
    val rideForRider2 = rideSharingApplication.requestRide(rider2.id, a1, a2, 3)

    println(rideSharingApplication.getAvailableDrivers.map(_.toString))
    println(rideSharingApplication.getActiveRides.map(_.toString))

    val rideForRider3 = rideSharingApplication.requestRide(rider2.id, a1, a2, 3)

  }

}
