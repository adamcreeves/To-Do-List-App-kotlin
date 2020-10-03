package com.example.todolistapp.models

data class Task(
    var taskName: String,
    var taskDescription: String? = null,
    var status: String
){
    companion object {
        const val COLLECTION_NAME = "tasks"
    }
}