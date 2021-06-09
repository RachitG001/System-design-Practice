package com.practice.jira.models

case class Task(
    id: Int,
    var sprintId: Int,
    var name: String,
    var taskType: TaskType,
    var taskStatus: TaskStatus,
    var descriptionOpt: Option[String] = None,
    var assignedUserId: Int
) {

  /**
    * Method to update sprint for a task
    */
  def updateSprint(sprintId: Int): Unit = {
    if (this.taskStatus == TaskStatus.Done) {
      throw new Error("Task already completed, cannot update the sprint")
    }
    this.sprintId = sprintId
  }

  /**
    * Method to update a task status
    */
  def updateTask(task: Task): Unit = {
    this.taskStatus = task.taskStatus
    this.taskType = task.taskType
    this.name = task.name
    this.descriptionOpt = task.descriptionOpt
    this.assignedUserId = task.assignedUserId
  }
}
