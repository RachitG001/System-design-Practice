package com.practice.rideSharingApp

import com.practice.rideSharingApp.user.{Driver, Rider, UserService}

class RideSharingApplicationService(
    userService: UserService
) {

  private final val amountPerKm = 20

  def createRideAndBookDriver(
      rideId: Int,
      riderId: Int,
      driver: Driver,
      origin: Address,
      destination: Address,
      noOfSeats: Int
  ): Ride = {
    val ride = Ride(rideId, riderId, driver.id, origin, destination, noOfSeats)
    userService.bookDriver(driver)
    ride
  }

  def completeRideAndCalculateAmount(
      ride: Ride,
      rider: Rider,
      driver: Driver
  ): Double = {
    val totalDistance = Address.calculateDistance(ride.origin, ride.destination)
    val isPreferredRider = rider.isPreferredRider
    val totalAmount = (isPreferredRider, ride.noOfSeats) match {
      case (true, 1) => totalDistance * amountPerKm * 0.75
      case (true, _) => totalDistance * ride.noOfSeats * amountPerKm * 0.5
      case (_, 1)    => totalDistance * amountPerKm
      case (_, _)    => totalDistance * ride.noOfSeats * amountPerKm * 0.75
    }
    ride.finishRide(totalAmount)
    userService.vacantDriver(driver)
    userService.updateRiderAfterRideCompletion(rider)
    totalAmount
  }

}
