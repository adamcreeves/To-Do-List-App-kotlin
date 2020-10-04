package com.example.todolistapp.models

data class Task(
    var taskName: String? = null,
    var taskDescription: String? = null
) {
    companion object {
        const val COLLECTION_NAME = "tasks"
    }
}