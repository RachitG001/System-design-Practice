package com.practice

import com.practice.passwordChecker.{Medium, PasswordChecker, Strong, Weak}
import org.scalatest.flatspec.AnyFlatSpec

class PasswordCheckerSpec extends AnyFlatSpec {

  val passwordChecker: PasswordChecker = PasswordChecker.default()

  "password checker" should "return weak if length less than 3" in {
    assert(passwordChecker.measureStrength("ab") == Weak)
  }

  it should "return weak if length greater than 20" in {
    assert(
      passwordChecker
        .measureStrength("we_always_leave_something_for_something") == Weak
    )
  }

  it should "return weak if less than 2 condition matches" in {
    assert(passwordChecker.measureStrength("random_word") == Weak)
  }

  it should "return medium if less than 3 condition matches" in {
    assert(passwordChecker.measureStrength("some_word") == Medium)
  }

  it should "return strong if all condition matches" in {
    assert(passwordChecker.measureStrength("rachit_dude") == Strong)
  }

}
