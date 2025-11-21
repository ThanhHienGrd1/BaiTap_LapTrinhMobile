package com.example.baitaptuan7_database.repository

import android.content.Context
import com.example.baitaptuan7_database.model.Task
import com.example.baitaptuan7_database.model.Subtask
import com.example.baitaptuan7_database.model.Attachment
import com.example.baitaptuan7_database.room.AppDatabase
import com.example.baitaptuan7_database.room.TaskEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).taskDao()
    private val gson = Gson()

    suspend fun getAllTasks(): List<Task> = dao.getAllTasks().map { it.toTask() }
    suspend fun getTaskById(id: Int): Task? = dao.getTaskById(id)?.toTask()
    suspend fun insertTask(task: Task) = dao.insertTask(task.toEntity())
    suspend fun deleteTask(task: Task) = dao.deleteTask(task.toEntity())
    suspend fun updateTask(task: Task) = dao.updateTask(task.toEntity())

    private fun TaskEntity.toTask(): Task {
        val subtaskType = object : TypeToken<List<Subtask>>() {}.type
        val attachmentType = object : TypeToken<List<Attachment>>() {}.type
        return Task(
            id, title, description, status, priority, category, createdAt, updatedAt,
            gson.fromJson(subtasksJson, subtaskType),
            gson.fromJson(attachmentsJson, attachmentType)
        )
    }

    private fun Task.toEntity(): TaskEntity =
        TaskEntity(
            id, title, description, status, priority, category, createdAt, updatedAt,
            gson.toJson(subtasks),
            gson.toJson(attachments)
        )
}
