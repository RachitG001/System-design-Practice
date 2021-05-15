package com.practice

import com.practice.cache.Cache

object Main {

  def main(args: Array[String]): Unit = {

    val cache = new Cache[String, String](5)
    cache.put("1", "One")
    cache.put("2", "Two")
    cache.put("3", "Three")
    println(cache.get("2"))
    println(cache.get("4"))
    cache.put("4", "Four")
    cache.put("5", "Five")
    cache.put("6", "Six")
    println(cache.get("4"))
    println(cache.get("6"))
  }
}
