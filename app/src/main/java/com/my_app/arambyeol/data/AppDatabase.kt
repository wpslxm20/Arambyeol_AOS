package com.my_app.arambyeol.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MealPlanEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealPlanDao(): MealPlanDao

    companion object {
        // 싱글톤 AppDatabase 인스턴스
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // AppDatabase 인스턴스를 반환하는 함수
        fun getDatabase(context: Context): AppDatabase {
            // 이미 인스턴스가 생성되어 있다면 반환
            return INSTANCE ?: synchronized(this) {
                // 새로운 인스턴스를 생성
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "meal_plan_database"
                )
                    // 데이터베이스 스키마가 변경될 때 기존 데이터를 버리고 새로운 스키마로 마이그레이션하는 방법을 설정.
                    // 실제 앱에서는 마이그레이션 전략을 제공해야 합니다.
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                // 생성된 인스턴스 반환
                instance
            }
        }
    }
}
