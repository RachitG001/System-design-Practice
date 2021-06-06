package com.practice.meetingScheduler.exceptions

trait RoomSchedulerException extends Throwable {
  val errorMessage: String

  override def getMessage: String = this.errorMessage
}

case object RoomAlreadyBookedException extends RoomSchedulerException {
  override val errorMessage: String =
    "This room is already booked, please book another room"
}

case object NoRoomAvailableException extends RoomSchedulerException {
  override val errorMessage: String =
    "All rooms are booked for this time slot, please change your timing"
}
