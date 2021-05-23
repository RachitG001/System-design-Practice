package com.practice.rideSharingApp.user

object UserType extends Enumeration {
  type UserType = Value
  val Admin = Value("Admin")
  val Driver = Value("Driver")
  val Rider = Value("Rider")
}
