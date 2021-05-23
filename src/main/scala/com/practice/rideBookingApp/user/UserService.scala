package com.practice.rideBookingApp.user

class UserService {

  def addRider(riderId: Int, name: String): Rider = {
    Rider(id = riderId, name = name)
  }

  def addDriver(driverId: Int, name: String): Driver = {
    Driver(id = driverId, name = name)
  }

  def bookDriver(driver: Driver): Unit = driver.isAvailable = false

  def vacantDriver(driver: Driver): Unit = {
    driver.isAvailable = true
    driver.ridesCompleted = driver.ridesCompleted + 1
  }

  def updateRiderAfterRideCompletion(rider: Rider): Unit = {
    rider.ridesCompleted = rider.ridesCompleted + 1
  }

}
