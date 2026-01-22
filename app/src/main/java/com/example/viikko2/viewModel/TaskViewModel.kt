package com.example.viikko2.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.viikko2.domain.Task
import com.example.viikko2.domain.todoItems

class TaskViewModel : ViewModel() {

    private val _tasks = mutableStateOf(listOf<Task>())
    val tasks: State<List<Task>> = _tasks

    init {
        _tasks.value = todoItems
    }

    fun addTask(task: Task) {
        _tasks.value += task
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) task.copy(done = !task.done)
            else task
        }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = _tasks.value.filter { it.done == done }
    }

    fun sortByDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }
}
