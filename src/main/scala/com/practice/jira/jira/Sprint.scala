package com.practice.jira.jira

import com.practice.jira.models.{Task, TaskStatus, TaskType, User}

class Sprint(
    val id: Int,
    name: String,
    startTime: Int,
    endTime: Option[Int] = None,
    private var tasks: Seq[Task] = Seq.empty,
    owner: User
) {

  private var userTaskMap: Map[Int, Seq[Task]] = Map.empty

  /**
    * Method to get sprint details
    */
  def showSprintDetails(): Unit = println(s"""Sprint id: ${this.id}
       |Sprint name: ${this.name}
       |Sprint started at: ${this.startTime}
       |Sprint ended at: ${this.endTime.getOrElse("")}
       |No of tasks: ${this.tasks.size}
       |Sprint owner: ${this.owner.name}
       |""".stripMargin)

  /**
    * Method to add task
    */
  def addTask(
      taskName: String,
      taskType: TaskType,
      taskStatus: TaskStatus,
      userAssigned: Int
  ): Int = {
    val taskId = this.tasks.size + 1
    val task = Task(
      id = taskId,
      sprintId = this.id,
      name = taskName,
      taskType = taskType,
      taskStatus = taskStatus,
      assignedUserId = userAssigned,
      descriptionOpt = None
    )
    this.tasks = this.tasks :+ task
    this.userTaskMap = this.userTaskMap.updated(
      task.assignedUserId,
      this.userTaskMap.getOrElse(task.assignedUserId, Seq.empty) :+ task
    )
    taskId
  }

  /**
    * Method to modify a task
    */
  def modifyTask(
      taskId: Int,
      taskName: Option[String] = None,
      taskType: Option[TaskType] = None,
      taskStatus: Option[TaskStatus] = None
  ): Unit = {
    val taskOpt = this.tasks.find(_.id == taskId)
    taskOpt match {
      case Some(task) =>
        val updatedTask = task.copy(
          name = taskName.getOrElse(task.name),
          taskType = taskType.getOrElse(task.taskType),
          taskStatus = taskStatus.getOrElse(task.taskStatus)
        )
        task.updateTask(updatedTask)
      case None => throw new Error("No task found with this id")
    }
  }

}
