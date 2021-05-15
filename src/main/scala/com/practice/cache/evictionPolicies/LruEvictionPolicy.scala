package com.practice.cache.evictionPolicies

import com.practice.dataStructures.{DoublyLinkedList, DoublyLinkedListNode}

class LruEvictionPolicy[T] extends EvictionPolicy[T] {

  private val ll: DoublyLinkedList[T] = new DoublyLinkedList[T]
  private var mapper: Map[T, DoublyLinkedListNode[T]] = Map.empty

  override def keyAccessed(key: T): Unit = {
    this.mapper.get(key) match {
      case Some(node) =>
        this.ll.ejectNode(node)
        this.ll.pushBackwards(node)
      case None =>
        val nodeToInsert = DoublyLinkedListNode(value = key)
        this.ll.pushBackwards(nodeToInsert)
        this.mapper = this.mapper + (key -> nodeToInsert)
    }
  }

  override def keyToRemove(): T = {
    try {
      val nodeToEject = this.ll.getHead
      this.ll.ejectNode(nodeToEject)
      this.mapper = this.mapper - (nodeToEject.getValue)
      nodeToEject.getValue
    } catch {
      case _: Error =>
        throw new Error("Map seems to be empty, error while removing the key")
    }
  }
}
