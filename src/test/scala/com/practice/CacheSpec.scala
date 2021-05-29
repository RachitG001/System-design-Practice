package com.practice

import com.practice.cache.Cache
import org.scalatest.flatspec.AnyFlatSpec

class CacheSpec extends AnyFlatSpec {

  val cache = new Cache[String, String](2)

  "cache" should "be empty initially" in {
    assert(cache.isCacheEmpty)
  }

  it should "able to store values in the cache" in {
    cache.put("1", "One")
    cache.put("2", "Two")
    assert(!cache.isCacheEmpty)
  }

  it should "fetch values if found or return none" in {
    assert(cache.get("1").contains("One"))
    assert(cache.get("2").contains("Two"))
    assert(cache.get("3").isEmpty)
  }

  it should "remove least recently used value when cache is full" in {
    cache.get("1")
    cache.put("3", "Three")
    assert(cache.get("3").contains("Three"))
    assert(cache.get("2").isEmpty)
  }

}
