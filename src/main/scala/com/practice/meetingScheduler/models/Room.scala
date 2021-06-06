package com.practice.meetingScheduler.models

class Room(
    roomNo: Int,
    private var meetings: Seq[Meeting]
) {

  def this(roomNo: Int) = {
    this(roomNo, meetings = Seq.empty)
  }

  def getRoomNo: Int = this.roomNo

  /**
    * Function to book the room for the meeting slot
    * @param meeting
    * @throws RoomAlreadyBookedException if already booked for that time slot
    */
  def bookRoom(startTime: Int, endTime: Int): Option[Meeting] = {
    val isRoomAvailable = this.meetings.forall { meeting =>
      if (meeting.endTime > startTime && meeting.startTime < endTime) {
        false
      } else {
        true
      }
    }
    if (isRoomAvailable) {
      val meeting =
        Meeting(startTime = startTime, endTime = endTime, roomNo = this.roomNo)
      this.meetings = this.meetings :+ meeting
      Some(meeting)
    } else {
      None
    }
  }

}
