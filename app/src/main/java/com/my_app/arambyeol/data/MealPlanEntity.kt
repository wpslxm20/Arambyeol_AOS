package com.my_app.arambyeol.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MealPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val day: String,
    val time: String,
    val listCourse: String // List<Course> 객체를 JSON 문자열로 저장
)

