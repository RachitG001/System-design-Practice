package com.practice.rideSharingApp.user

import com.practice.rideSharingApp.user.UserType.UserType

trait User {
  def id: Int
  def name: String
  def userType: UserType
  def ridesCompleted: Int
}

class Driver(
    override val id: Int,
    override var name: String
) extends User {
  override def userType: UserType = UserType.Driver

  override def ridesCompleted: Int = 0
}

class Rider(
    override val id: Int,
    override var name: String
) extends User {
  override def userType: UserType = UserType.Rider

  override def ridesCompleted: Int = 0

  def isPreferredRider: Boolean = ridesCompleted >= 10
}
