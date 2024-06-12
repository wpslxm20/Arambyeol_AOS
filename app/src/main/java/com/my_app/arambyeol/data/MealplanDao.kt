package com.my_app.arambyeol.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealPlanDao {
    @Insert
    suspend fun insertListCourse(mealPlanEntity: MealPlanEntity)

    @Query("SELECT * FROM MealPlanEntity")
    suspend fun getMealPlan(): List<MealPlanEntity>

    @Query("SELECT * FROM MealPlanEntity WHERE day= :day AND time= :time")
    suspend fun getListCourse(day: String, time: String): MealPlanEntity

    @Query("DELETE FROM MealPlanEntity")
    suspend fun deleteAllCourse()
}
