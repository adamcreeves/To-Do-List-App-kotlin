package com.example.todolistapp.models

data class Task(
    var taskName: String? = null,
    var taskDescription: String? = null,
    var status: String? = "incomplete"
) {
    companion object {
        const val COLLECTION_NAME = "tasks"
    }
}