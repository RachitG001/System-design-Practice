package com.practice.jira.models

sealed abstract case class TaskType(value: String)

object TaskType {

  object Story extends TaskType("Story")
  object Feature extends TaskType("Feature")
  object Bug extends TaskType("Bug")

  def availableTypes: Set[TaskType] = Set(Story, Feature, Bug)
}
