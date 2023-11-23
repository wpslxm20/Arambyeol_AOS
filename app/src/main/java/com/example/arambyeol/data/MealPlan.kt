package com.example.arambyeol.data

data class MealPlan(
    val today: DayPlan,
    val tomorrow: DayPlan,
    val theDayAfterTomorrow: DayPlan
)

data class DayPlan(
    val morning: List<Course>,
    val lunch: List<Course>,
    val dinner: List<Course>
)

data class Course(
    val course: String,
    val menu: List<String>
)
