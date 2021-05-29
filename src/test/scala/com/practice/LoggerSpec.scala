package com.practice

import com.practice.logger.logger.{LoggerClient, LoggerClientImpl}
import org.scalatest.flatspec.AnyFlatSpec

class LoggerSpec extends AnyFlatSpec {

  val logger: LoggerClient = new LoggerClientImpl

  "logger" should "initially have no active process" in {
    assert(logger.getActiveProcessesCount == 0)
  }

  it should "be able to add processes" in {
    logger.start("2")
    logger.start("3")
    logger.start("1")
    assert(logger.getActiveProcessesCount == 3)
  }

  it should "be able end and poll processes" in {
    logger.end("2")
    logger.poll()
    assert(logger.getActiveProcessesCount == 2)
  }
}
