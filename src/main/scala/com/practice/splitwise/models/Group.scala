package com.practice.splitwise.models

case class Group(
    id: String,
    title: String,
    userIds: Seq[String]
)
