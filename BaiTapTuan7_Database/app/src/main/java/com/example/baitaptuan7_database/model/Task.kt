package com.example.baitaptuan7_database.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val createdAt: String,
    val updatedAt: String,
    val subtasks: List<Subtask>,
    val attachments: List<Attachment>
)
