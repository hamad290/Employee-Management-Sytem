package com.example.employeemanagementsystem

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(employee: Employee)

    @Update
    suspend fun update(employee: Employee)

    @Delete
    suspend fun delete(employee: Employee)

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): LiveData<List<Employee>>
}
