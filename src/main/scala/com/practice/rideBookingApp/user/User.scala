package com.practice.rideBookingApp.user

import com.practice.rideBookingApp.user.UserType.UserType

trait User {
  val id: Int
  var name: String
  val userType: UserType

  var ridesCompleted: Int = 0
  override def toString: String = this.name
}

case class Driver(
    override val id: Int,
    override var name: String
) extends User {
  override val userType: UserType = UserType.Driver

  var isAvailable: Boolean = true
}

case class Rider(
    override val id: Int,
    override var name: String
) extends User {
  override val userType: UserType = UserType.Rider

  def isPreferredRider: Boolean = this.ridesCompleted >= 10
}
