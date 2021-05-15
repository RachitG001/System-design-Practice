package com.practice.cache

import com.practice.cache.evictionPolicies.EvictionPolicy
import com.practice.cache.storage.Storage
import com.practice.cache.exceptions.StorageFullException

class Cache[K, V](
    private var cacheSize: Int,
    private val evictionPolicy: EvictionPolicy[K]
) {
  private val storage = new Storage[K, V](cacheSize)

  def put(key: K, value: V): Unit = {
    try {
      this.storage.add(key, value)
      this.evictionPolicy.keyAccessed(key)
    } catch {
      case ex: StorageFullException =>
        try {
          val keyToRemove = this.evictionPolicy.keyToRemove()
          this.storage.remove(keyToRemove)
          this.storage.add(key, value)
          this.evictionPolicy.keyAccessed(key)
        } catch {
          case error: Error =>
            println(
              s"Error while inserting element to cache. ${error.getMessage}"
            )
        }
    }
  }

  def get(key: K): Option[V] = {
    this.storage.find(key) match {
      case Some(value) =>
        this.evictionPolicy.keyAccessed(key)
        Some(value)
      case None =>
        println(s"No value find for key: $key")
        None
    }
  }
}
