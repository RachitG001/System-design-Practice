package com.practice.logger.logger

trait LoggerClient {

  /**
    * When a process starts, it calls start with process id
    */
  def start(processId: String): Unit

  /**
    * When a process ends, it calls ends with the process id
    */
  def end(processId: String): Unit

  /**
    * Polls the first log entry of the completed process sorted by the start time in the format below:
    *Process with id: {processId} started at {startTime} and ended at {endTime}
    *<p>
    * process id = 1 --> 12, 15
    * process id = 2 --> 8, 12
    * process id = 3 --> 11, 13
    * <p>
    * Process with id: 2 started at 8 and ended at 12
    * Process with id: 3 started at 11 and ended at 13
    * Process with id: 1 starteda at 12 and ended at 15
    */
  def poll(): Unit

  /**
    * Get the no of active processes in the queue
    */
  def getActiveProcessesCount: Int
}
