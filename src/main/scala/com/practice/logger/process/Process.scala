package com.practice.logger.process

case class Process(
    processId: String,
    startTime: Long,
    var endTimeOpt: Option[Long] = None
) {
  def endProcess(endTime: Long): Unit = this.endTimeOpt = Some(endTime)

  def getProcessDetails(): Unit = {
    println(
      s"Process with id:${this.processId} started at ${this.startTime}...."
    )
  }
}
