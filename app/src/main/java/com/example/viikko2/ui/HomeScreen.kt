package com.example.viikko2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko2.domain.Task
import com.example.viikko2.viewModel.TaskViewModel

@Composable
fun HomeScreen(
    title: String,
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // App title
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )


        val taskList by viewModel.tasks

        // Todo list task rows
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(taskList) { task ->
                Card {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(task.title, fontWeight = FontWeight.Bold, modifier = Modifier.weight(6f))
                            Text("${task.priority}", modifier = Modifier.weight(1f))
                            Text(task.dueDate, modifier = Modifier.weight(4f))
                            IconToggleButton(
                                checked = task.done,
                                onCheckedChange = { viewModel.toggleDone(task.id) }
                            ) {
                                if (task.done) Icon(Icons.Filled.CheckCircle, contentDescription = "Done")
                                else Icon(Icons.Outlined.CheckCircle, contentDescription = "Not Done")
                            }
                            IconButton(
                                onClick = { viewModel.removeTask(task.id) }
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Remove")
                            }

                        }
                        if (task.description.isNotEmpty()) Text(text = task.description)
                    }
                }
            }
        }

        // Add tasks
        var text by remember { mutableStateOf("") }

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Write tasks") },
                modifier = Modifier.weight(4f)
            )

            IconButton(
                onClick = {
                    val newTask = Task(
                        id = taskList.size + 1,
                        title = text,
                        priority = 1,
                        description = "",
                        dueDate = "2026-01-18",
                        done = false
                    )
                    viewModel.addTask(newTask)
                    text = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    Icons.Outlined.AddCircle,
                    contentDescription = "Add task",
                )
            }
        }

        // Filter and sort buttons
        Row {
            Button(onClick = { viewModel.sortByDueDate() }) { Text("Sort By Due Date") }
            Button(onClick = { viewModel.filterByDone(true) }) { Text("Filter By Done") }
        }
    }
}
