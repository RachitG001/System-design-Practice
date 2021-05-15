package com.practice.cache.evictionPolicies

/**
  * Interface for defining eviction policies
  * @tparam K : Type of key
  */

trait EvictionPolicy[K] {

  /**
    *
    * @param key : Key which is recently accessed
    */
  def keyAccessed(key: K): Unit

  /**
    *
    * @return key which is to be evicted
    */
  def keyToRemove(): K
}
