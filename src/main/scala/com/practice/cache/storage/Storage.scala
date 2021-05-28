package com.practice.cache.storage

import com.practice.cache.exceptions.StorageFullException

class Storage[K, V](
    private var maxSize: Int = 10
) {
  private var _store: Map[K, V] = Map.empty

  def isStorageEmpty: Boolean = this._store.isEmpty

  def add(key: K, value: V): Unit = {
    if (_store.size == maxSize) {
      throw new StorageFullException
    }
    _store = _store + (key -> value)
  }

  def find(key: K): Option[V] = _store.get(key)

  def remove(key: K): Unit = {
    if (_store.isEmpty) {
      throw new Error("Storage already empty")
    }
    _store = _store - key
  }
}
