package com.practice.logger.logger

import com.practice.logger.process.Process

import scala.collection.mutable

class LoggerClientImpl extends LoggerClient {

  private var processCollection: Map[String, Process] = Map.empty
  private val processHeap: mutable.PriorityQueue[(Long, Process)] =
    mutable.PriorityQueue.empty(
      Ordering.by[(Long, Process), Long](_._1).reverse
    )

  override def start(processId: String): Unit = {
    val process = Process(processId, System.currentTimeMillis())
    this.processCollection += (
      processId -> process
    )
    this.processHeap += ((process.startTime, process))
    println(this.processCollection)
  }

  override def end(processId: String): Unit = {
    this.processCollection
      .get(processId)
      .foreach(_.endProcess(System.currentTimeMillis()))
    println(this.processCollection)
  }

  override def poll(): Unit = {
    val pollProcess = this.processHeap.headOption
    pollProcess match {
      case Some((_, process)) if process.endTimeOpt.isDefined =>
        println(
          s"Process with id: ${process.processId} started at ${process.startTime} and ended at ${process.endTimeOpt.get}"
        )
        this.processCollection -= process.processId
        this.processHeap.dequeue()
      case Some(_) => println("No completed process found in the queue")
      case None =>
        println("No process found in the queue")
    }
  }

  override def getActiveProcessesCount: Int = this.processCollection.size
}
