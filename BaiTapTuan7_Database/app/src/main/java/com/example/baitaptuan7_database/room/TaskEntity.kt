package com.example.baitaptuan7_database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val createdAt: String,
    val updatedAt: String,
    val subtasksJson: String,      // lưu List<Subtask> dạng JSON
    val attachmentsJson: String    // lưu List<Attachment> dạng JSON
)
