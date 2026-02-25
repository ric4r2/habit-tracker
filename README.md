# Habit Tracker App

![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)
![Platform](https://img.shields.io/badge/Platform-Android-green)
![Language](https://img.shields.io/badge/Language-Kotlin-blue)

A modern Android app built with Jetpack Compose to track habits with individual timers for each activity.

## Project Status

This project is currently **in active development**. Features and functionality are being added and refined.

## Features

**Core Features:**
- Create and manage multiple habits
- Individual timer for each habit
- Track total time spent on each habit
- Color-coded habits for easy identification
- Persistent storage using Room database
- Material 3 design

## App Functionality

### Habit Management
- **Add Habits**: Tap the floating action button (+) to create a new habit
- **Customize**: Give each habit a name, optional description, and color
- **Delete**: Remove habits with confirmation dialog

### Timer Functionality
- **Start/Stop**: Control timer for each habit independently
- **Real-time Display**: Timer updates every second when running
- **Total Time**: Accumulated time is saved across multiple timer sessions
- **Reset**: Clear all tracked time for a habit
- **Persistent**: Running timers survive app restarts

### UI Features
- Material Design 3
- Color-coded habit cards
- Real-time timer updates
- Empty state for first-time users
- Confirmation dialogs for destructive actions

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room
- **Coroutines**: Asynchronous operations and Flow
- **Build System**: Gradle with Version Catalog

## Project Structure

```
app/src/main/java/com/example/practica1/
├── data/
│   ├── Habit.kt              # Data model
│   ├── HabitDao.kt           # Database access object
│   ├── HabitDatabase.kt      # Room database
│   └── HabitRepository.kt    # Data repository
├── viewmodel/
│   └── HabitViewModel.kt     # Business logic and state management
├── ui/
│   ├── components/
│   │   ├── AddEditHabitDialog.kt  # Dialog for creating/editing habits
│   │   └── HabitCard.kt           # Individual habit display
│   ├── screens/
│   │   └── HabitTrackerScreen.kt  # Main screen
│   └── theme/                      # App theme
├── utils/
│   └── TimeUtils.kt          # Time formatting utilities
└── MainActivity.kt           # Entry point
```

## How to Use

1. **First Launch**: You'll see an empty state with instructions
2. **Add a Habit**: 
   - Tap the + button
   - Enter habit name and optional description
   - Choose a color
   - Tap "Save"
3. **Track Time**:
   - Tap "Start" to begin timing
   - Tap "Stop" to pause
   - Time accumulates across sessions
   - Use "Reset" to clear the timer
4. **Delete a Habit**: Tap the trash icon and confirm

## Time Display Format
- Format: `HH:MM:SS` or `MM:SS` (hours only shown when > 0)
- Detailed view: `Xh Ym Zs`
- Real-time updates every second

## Future Enhancements (Ideas)
- Statistics and analytics
- Daily/weekly goals
- Notifications and reminders
- Data export
- Habit streaks
- Categories and tags
- Charts and visualizations

## License
This project is for educational purposes.
