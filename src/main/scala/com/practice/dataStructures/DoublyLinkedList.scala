package com.practice.dataStructures

/**
  *
  * @tparam T: Type of value to be stored in linked list
  */

case class DoublyLinkedListNode[T](
    var value: T,
    var prev: DoublyLinkedListNode[T] = null,
    var next: DoublyLinkedListNode[T] = null
) {
  def getValue: T =
    if (this.value == null)
      throw new NoSuchElementException("No value available")
    else this.value

  def getNext: DoublyLinkedListNode[T] =
    if (this.next == null) throw new NoSuchElementException("No next available")
    else this.next

  def getPrev: DoublyLinkedListNode[T] =
    if (this.prev == null) throw new NoSuchElementException("No prev available")
    else this.prev
}

class DoublyLinkedList[T] {

  private var head: DoublyLinkedListNode[T] = null
  private var tail: DoublyLinkedListNode[T] = null

  def getHead: DoublyLinkedListNode[T] = {
    if (head == null) {
      throw new NoSuchElementException("No head element found")
    }
    this.head
  }

  def pushBackwards(nodeToInsert: DoublyLinkedListNode[T]): Unit = {
    if (nodeToInsert == null) {
      throw new NoSuchElementException("no node found to insert")
    }
    if (tail == null) {
      head = nodeToInsert
      tail = nodeToInsert
    } else {
      tail.next = nodeToInsert
      nodeToInsert.prev = tail
      tail = nodeToInsert
      tail.next = null
    }
  }

  def ejectNode(nodeToDelete: DoublyLinkedListNode[T]): Unit = {
    nodeToDelete match {
      case null =>
        throw new NoSuchElementException("no node exists")
      case node if node == head =>
        head = head.next
        if (head != null) {
          head.prev = null
        }
      case node if node == tail =>
        tail = tail.prev
        tail.next = null
      case node =>
        node.prev.next = node.next
        node.next.prev = node.prev
    }
  }

  def printAllNodes(): Unit = {
    var sNode = head
    while (sNode != null) {
      println(sNode.getValue)
      sNode = sNode.next
    }
  }
}
