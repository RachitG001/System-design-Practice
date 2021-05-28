package com.practice

import com.practice.cache.Cache
import org.scalatest.flatspec.AnyFlatSpec

class CacheSpec extends AnyFlatSpec {

  val cache = new Cache[String, String](3)

  "cache" should "be empty initially" in {
    assert(cache.isCacheEmpty)
  }
}
