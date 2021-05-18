package com.practice.passwordChecker

trait PasswordCheckPolicy {
  def matches(password: String): Boolean
}

case class LengthCheckPolicy(
    minLength: Int = 1,
    maxLength: Int = 30
) extends PasswordCheckPolicy {
  override def matches(password: String): Boolean = {
    val passwordLength = password.length
    passwordLength >= minLength && passwordLength <= maxLength
  }
}

case class ContainsCheckPolicy(
    values: Set[String]
) extends PasswordCheckPolicy {
  override def matches(password: String): Boolean = {
    this.values.exists(s => password contains s)
  }
}

case class NotContainsCheckPolicy(
    values: Set[String]
) extends PasswordCheckPolicy {
  override def matches(password: String): Boolean = {
    !ContainsCheckPolicy(values).matches(password)
  }

}
