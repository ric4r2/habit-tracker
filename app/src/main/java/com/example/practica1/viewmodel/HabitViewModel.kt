package com.example.practica1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.data.Habit
import com.example.practica1.data.HabitDatabase
import com.example.practica1.data.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HabitRepository
    val allHabits: StateFlow<List<Habit>>

    private val _currentTime = MutableStateFlow(System.currentTimeMillis())
    val currentTime: StateFlow<Long> = _currentTime.asStateFlow()

    init {
        val habitDao = HabitDatabase.getDatabase(application).habitDao()
        repository = HabitRepository(habitDao)
        
        val habitsFlow = MutableStateFlow<List<Habit>>(emptyList())
        allHabits = habitsFlow.asStateFlow()
        
        viewModelScope.launch {
            repository.allHabits.collect { habits ->
                habitsFlow.value = habits
            }
        }

        // Update current time every second for active timers
        viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(1000)
                _currentTime.value = System.currentTimeMillis()
            }
        }
    }

    fun insertHabit(name: String, description: String, color: Long) {
        viewModelScope.launch {
            val habit = Habit(
                name = name,
                description = description,
                color = color
            )
            repository.insertHabit(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            repository.updateHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    fun startTimer(habit: Habit) {
        viewModelScope.launch {
            val updatedHabit = habit.copy(
                isTimerRunning = true,
                timerStartTime = System.currentTimeMillis()
            )
            repository.updateHabit(updatedHabit)
        }
    }

    fun stopTimer(habit: Habit) {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            val elapsedTime = if (habit.isTimerRunning) {
                currentTime - habit.timerStartTime
            } else {
                0L
            }
            
            val updatedHabit = habit.copy(
                isTimerRunning = false,
                timerStartTime = 0,
                totalTimeSpent = habit.totalTimeSpent + elapsedTime
            )
            repository.updateHabit(updatedHabit)
        }
    }

    fun resetTimer(habit: Habit) {
        viewModelScope.launch {
            val updatedHabit = habit.copy(
                isTimerRunning = false,
                timerStartTime = 0,
                totalTimeSpent = 0
            )
            repository.updateHabit(updatedHabit)
        }
    }

    fun getCurrentElapsedTime(habit: Habit): Long {
        return if (habit.isTimerRunning) {
            habit.totalTimeSpent + (_currentTime.value - habit.timerStartTime)
        } else {
            habit.totalTimeSpent
        }
    }
}
