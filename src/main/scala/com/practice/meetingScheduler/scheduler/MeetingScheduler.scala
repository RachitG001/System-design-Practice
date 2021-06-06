package com.practice.meetingScheduler.scheduler

import com.practice.meetingScheduler.exceptions.NoRoomAvailableException
import com.practice.meetingScheduler.models.{Meeting, Room}

import scala.util.control.Breaks._

class MeetingScheduler {

  private var rooms: Map[Int, Room] = Map.empty

  /**
    * Function to add a room to the application
    */
  def addRoom(roomNo: Int): Unit = {
    if (this.rooms.contains(roomNo)) {
      throw new Error(s"A room with no: $roomNo already exists")
    }
    this.rooms = this.rooms.updated(roomNo, new Room(roomNo))
  }

  /**
    * Function to book a room for a meeting given its start and end time
    */
  def bookMeeting(
      startTime: Int,
      endTime: Int
  ): Meeting = {
    var meetingBooked: Option[Meeting] = None
    breakable {
      this.rooms.foreach {
        case (_, room) =>
          val meetingBookedOpt = room.bookRoom(startTime, endTime)
          if (meetingBookedOpt.isDefined) {
            meetingBooked = meetingBookedOpt
            break()
          }
      }
    }
    meetingBooked match {
      case Some(meeting) => meeting
      case None          => throw NoRoomAvailableException
    }
  }

}
