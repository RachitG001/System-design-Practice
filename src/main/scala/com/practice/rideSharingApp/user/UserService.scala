package com.practice.rideSharingApp.user

class UserService {

  def addRider(riderId: Int, name: String): Rider = {
    new Rider(id = riderId, name = name)
  }

  def addDriver(driverId: Int, name: String): Driver = {
    new Driver(id = driverId, name = name)
  }

}
