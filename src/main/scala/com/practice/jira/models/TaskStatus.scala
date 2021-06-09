package com.practice.jira.models

sealed abstract case class TaskStatus(status: String)

object TaskStatus {

  object ToDo extends TaskStatus("To do")
  object InProgress extends TaskStatus("In progress")
  object Testing extends TaskStatus("Testing")
  object Done extends TaskStatus("Done")

  def availableStatus: Set[TaskStatus] = Set(ToDo, InProgress, Testing, Done)
}
