package com.practice.jira.jira

import com.practice.jira.models.{TaskStatus, TaskType, User}

import scala.util.Random

class Jira(
    private var userList: Seq[User] = Seq.empty,
    private var sprints: Seq[Sprint] = Seq.empty
) {

  /**
    * Method to add a sprint
    */
  def addSprint(sprintName: String, userId: Int, startTime: Int): Int = {
    val userOpt = this.userList.find(_.id == userId)
    userOpt match {
      case Some(user) =>
        val sprint = new Sprint(
          id = this.sprints.size + 1,
          name = sprintName,
          startTime = startTime,
          owner = user
        )
        this.sprints = this.sprints.appended(sprint)
        sprint.id
      case None =>
        throw new Error("No user found for this user id")
    }
  }

  /**
    * Method to add a task to a sprint
    */
  def addTask(
      sprintId: Int,
      taskName: String,
      taskType: TaskType,
      taskStatus: TaskStatus,
      userAssigned: Int
  ): Int = {
    this.sprints.find(_.id == sprintId) match {
      case Some(sprint) =>
        sprint.addTask(taskName, taskType, taskStatus, userAssigned)
      case None =>
        throw new Error("No sprint found for this id")
    }
  }

  /**
    * Method to update task details
    */
  def updateTask(
      sprintId: Int,
      taskId: Int,
      taskName: Option[String] = None,
      taskType: Option[TaskType] = None,
      taskStatus: Option[TaskStatus] = None
  ): Unit = {
    this.sprints.find(_.id == sprintId) match {
      case Some(sprint) =>
        sprint.modifyTask(taskId, taskName, taskType, taskStatus)
      case None =>
        throw new Error("No sprint found for this id")
    }
  }

  /**
    * Method to show sprint details
    */
  def showSprintDetails(sprintId: Int, userId: Int): Unit = {
    this.sprints.find(_.id == sprintId) match {
      case Some(sprint) =>
        sprint.showSprintDetails()
      case None =>
        throw new Error("No sprint found for this id")
    }
  }

  /**
    * Method to add a user
    */
  def addUser(userName: String): Int = {
    val user = User(id = Random.between(1, 10), name = userName)
    this.userList = this.userList :+ user
    user.id
  }

}
