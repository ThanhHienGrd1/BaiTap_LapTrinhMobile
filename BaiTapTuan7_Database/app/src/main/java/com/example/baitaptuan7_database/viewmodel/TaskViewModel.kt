package com.example.baitaptuan7_database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.baitaptuan7_database.model.Attachment
import com.example.baitaptuan7_database.model.Subtask
import com.example.baitaptuan7_database.model.Task
import com.example.baitaptuan7_database.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = TaskRepository(app)
    init {
        // chen du lieu
        insertSampleTask()
    }
    //du lieu mau
    fun insertSampleTask() {
        val task = Task(
            id = 1,
            title = "Complete Android Project",
            description = "Finish UI, integrate API, write docs",
            status = "In Progress",
            priority = "High",
            category = "Work",
            createdAt = "2024-03-24T09:00:00A",
            updatedAt = "2024-03-25T09:00:00Z",
            subtasks = listOf(
                Subtask(11, "Team Meeting", true),
                Subtask(12, "Prepare slides", false),
                Subtask(13, "Revise topics", false)
            ),
            attachments = listOf(
                Attachment(100, "document_1_0.pdf", "https://example.com/document_1_0.pdf")
            )
        )

        viewModelScope.launch(Dispatchers.IO) {
            repo.insertTask(task)
        }
    }


    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _taskDetail = MutableStateFlow<Task?>(null)
    val taskDetail: StateFlow<Task?> = _taskDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try { _tasks.value = repo.getAllTasks() }
            catch (e: Exception) { _errorMessage.value = e.message }
            finally { _isLoading.value = false }
        }
    }

    fun loadTaskDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try { _taskDetail.value = repo.getTaskById(id) }
            catch (e: Exception) { _errorMessage.value = e.message }
            finally { _isLoading.value = false }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            _taskDetail.value?.let {
                repo.deleteTask(it)
                _tasks.value = _tasks.value.filterNot { t -> t.id == id }
            }
        }
    }

    fun deleteSelectedSubtasks(taskId: Int, selectedIndexes: List<Int>) {
        viewModelScope.launch {
            val task = _taskDetail.value ?: return@launch
            val remaining = task.subtasks.filterIndexed { i, _ -> i !in selectedIndexes }
            val updatedTask = task.copy(subtasks = remaining)
            repo.updateTask(updatedTask)
            _taskDetail.value = updatedTask
            _tasks.value = _tasks.value.map { if (it.id == taskId) updatedTask else it }
        }
    }
}
