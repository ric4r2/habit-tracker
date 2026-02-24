package com.example.practica1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val color: Long = 0xFF6200EE,
    val createdAt: Long = System.currentTimeMillis(),
    val totalTimeSpent: Long = 0, // in milliseconds
    val isTimerRunning: Boolean = false,
    val timerStartTime: Long = 0
)
