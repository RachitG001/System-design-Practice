package com.practice

import com.practice.logger.logger.LoggerClientImpl

object Main {

  def main(args: Array[String]): Unit = {

    val logger = new LoggerClientImpl
    logger.start("2")
    Thread.sleep(4000)
    logger.start("3")
    Thread.sleep(4000)
    logger.start("1")
    Thread.sleep(4000)
    logger.poll()
    logger.end("3")
    Thread.sleep(3000)
    logger.poll()
    logger.end("2")
    logger.poll()
    Thread.sleep(4000)
    logger.end("1")
    logger.poll()
  }

}
