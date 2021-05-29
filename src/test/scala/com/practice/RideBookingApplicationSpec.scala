package com.practice

import com.practice.rideBookingApp.ride.Address
import com.practice.rideBookingApp.rideBookingApplication.RideBookingApplication
import com.practice.rideBookingApp.user.UserType
import org.scalatest.flatspec.AnyFlatSpec

class RideBookingApplicationSpec extends AnyFlatSpec {

  val rideBookingApp = new RideBookingApplication()

  val location1: Address = Address("Bangalore", "Karnataka")
  val location2: Address = Address("New Delhi", "Delhi")

  "rideBookingApp" should "initially have no active rides & no drivers" in {
    assert(rideBookingApp.getActiveRides.isEmpty)
    assert(rideBookingApp.getAvailableDrivers.isEmpty)
  }

  it should "be able to add a rider" in {
    val rider = rideBookingApp.addRider("Rachit")
    assert(rider.userType == UserType.Rider)
    assert(rider.ridesCompleted == 0)
  }

  it should "throw error if no available drivers" in {
    val rider = rideBookingApp.addRider("Random user1")
    assertThrows[Error](
      rideBookingApp.requestRide(rider.id, location1, location2, noOfSeats = 1)
    )
  }

  it should "be able to add drivers" in {
    val driver1 = rideBookingApp.addDriver("ironman")
    val driver2 = rideBookingApp.addDriver("superman")
    assert(driver1.userType == UserType.Driver)
    assert(driver1.isAvailable)

    assert(driver2.userType == UserType.Driver)
    assert(rideBookingApp.getAvailableDrivers.size == 2)
  }

  it should "be able to book a ride" in {
    val rider = rideBookingApp.addRider("Random user2")
    val ride =
      rideBookingApp.requestRide(rider.id, location1, location2, noOfSeats = 2)
    assert(ride.isActive)
    assert(ride.endTime.isEmpty)
    assert(rideBookingApp.getActiveRides.size == 1)
  }

  it should "be able to complete the ride and return the amount" in {
    val activeRide = rideBookingApp.getActiveRides.head
    val rideAmount = rideBookingApp.completeRide(activeRide.id)

    assert(!activeRide.isActive)
    assert(activeRide.endTime.isDefined)

    assertThrows[Error](rideBookingApp.completeRide(activeRide.id))
    assertThrows[Error](rideBookingApp.completeRide(123))
  }
}
