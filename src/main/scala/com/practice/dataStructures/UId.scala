package com.practice.dataStructures

import java.util.UUID

sealed class UId(
    uuid: UUID
) {
  override val toString: String = uuid.toString
}

object UId {
  def generateNewUId: UId = new UId(UUID.randomUUID())
}
