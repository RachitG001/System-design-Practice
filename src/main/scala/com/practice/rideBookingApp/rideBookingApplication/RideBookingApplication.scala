package com.practice.rideBookingApp.rideBookingApplication

import com.practice.rideBookingApp.ride.{Address, Ride}
import com.practice.rideBookingApp.user.{Driver, Rider, UserService}

class RideBookingApplication {

  private final val userService = new UserService
  private final val utilityService = new RideBookingService(userService)

  var activeRiders: Seq[Rider] = Seq.empty
  var activeDrivers: Seq[Driver] = Seq.empty
  var rides: Seq[Ride] = Seq.empty

  def getActiveRides: Seq[Ride] = rides.filter(_.isActive)
  def getCompletedRides: Seq[Ride] = rides.filterNot(_.isActive)

  def getAvailableDrivers: Seq[Driver] = activeDrivers.filter(_.isAvailable)

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

  def requestRide(
      riderId: Int,
      origin: Address,
      destination: Address,
      noOfSeats: Int
  ): Ride = {
    val availableDrivers = this.getAvailableDrivers
    if (availableDrivers.isEmpty) {
      throw new Error("No riders available")
    } else {
      val availableDriver = availableDrivers.head
      val rideId = rides.size + 1
      val requestedRide = utilityService.createRideAndBookDriver(
        rideId,
        riderId,
        availableDriver,
        origin,
        destination,
        noOfSeats
      )
      this.rides = this.rides :+ requestedRide
      requestedRide
    }
  }

  def completeRide(rideId: Int): Double = {
    val rideOpt = this.rides.find(_.id == rideId)
    rideOpt match {
      case Some(ride) if ride.isActive =>
        val riderOpt = this.activeRiders.find(_.id == ride.userId)
        val driverOpt = this.activeDrivers.find(_.id == ride.driverId)
        (riderOpt, driverOpt) match {
          case (Some(rider), Some(driver)) =>
            utilityService.completeRideAndCalculateAmount(ride, rider, driver)
          case (_, None) =>
            throw new Error("No corresponding driver found for this ride")
          case (None, _) =>
            throw new Error("No corresponding rider found for this ride")
        }
      case Some(_) => throw new Error("Ride is already completed")
      case None    => throw new Error("No ride found with this ride id")
    }
  }

}
