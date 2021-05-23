package com.practice.rideSharingApp

import com.practice.rideSharingApp.user.{Driver, Rider, UserService}

class RideSharingApplication {

  private final val userService = new UserService
  var activeRiders: Seq[Rider] = Seq.empty
  var activeDrivers: Seq[Driver] = Seq.empty
  var rides: Seq[Ride] = Seq.empty

  def getActiveRides: Seq[Ride] = rides.filter(_.isActive)
  def getCompletedRides: Seq[Ride] = rides.filterNot(_.isActive)

  def addRider(name: String): Rider = {
    val newRiderId = activeRiders.size + 1
    val newRider = this.userService.addRider(newRiderId, name)
    this.activeRiders = this.activeRiders :+ newRider
    newRider
  }

  def addDriver(name: String): Driver = {
    val newDriverId = activeDrivers.size + 1
    val newDriver = this.userService.addDriver(newDriverId, name)
    this.activeDrivers = this.activeDrivers :+ newDriver
    newDriver
  }
}
