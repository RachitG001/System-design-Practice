package com.practice.passwordChecker

trait PasswordStrength {
  def displayValue: String
}
object Weak extends PasswordStrength {
  override def displayValue: String = "Weak"
}
object Medium extends PasswordStrength {
  override def displayValue: String = "Medium"
}
object Strong extends PasswordStrength {
  override def displayValue: String = "Strong"
}
