package com.practice.meetingScheduler.models

import com.practice.dataStructures.UId

case class Meeting(
    private val id: UId = UId.generateNewUId,
    startTime: Int,
    endTime: Int,
    roomNo: Int
) {
  override def toString: String =
    s"Meeting with id:$id booked at room no:$roomNo starting from $startTime and ending at $endTime"
}
