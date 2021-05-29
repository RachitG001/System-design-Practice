package com.practice.passwordChecker

sealed class PasswordChecker(
    conditions: Seq[PasswordCheckPolicy]
) {

  def measureStrength(password: String): PasswordStrength = {
    var strength = 0
    conditions.foreach { condition =>
      if (condition.matches(password)) {
        strength = strength + 1
      }
    }
    if (strength >= 3)
      Strong
    else if (strength == 2)
      Medium
    else Weak
  }

}

object PasswordChecker {

  def default(): PasswordChecker = {
    val defaultPolicyToUse: Seq[PasswordCheckPolicy] = Seq(
      NotContainsCheckPolicy(Set("random", "randomguy")),
      LengthCheckPolicy(3, 20),
      ContainsCheckPolicy(Set("rachit", "garg", "groot", "rachitgarg"))
    )
    new PasswordChecker(defaultPolicyToUse)
  }

}
