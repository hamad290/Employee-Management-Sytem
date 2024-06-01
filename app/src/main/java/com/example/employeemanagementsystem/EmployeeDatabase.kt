package com.example.employeemanagementsystem

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var instance: EmployeeDatabase? = null

        fun getDatabase(context: Context): EmployeeDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, EmployeeDatabase::class.java, "employee_db")
                .build()
    }
}
