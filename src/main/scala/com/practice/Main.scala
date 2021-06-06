package com.practice

import com.practice.meetingScheduler.scheduler.MeetingScheduler

object Main {

  def main(args: Array[String]): Unit = {

    val scheduler = new MeetingScheduler
    scheduler.addRoom(1)
    scheduler.addRoom(2)
    val meeting1 = scheduler.bookMeeting(3, 5)
    println(meeting1)
    val meeting2 = scheduler.bookMeeting(3, 6)
    println(meeting2)
    scheduler.bookMeeting(4, 6)
  }

}
