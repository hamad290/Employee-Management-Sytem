package com.example.employeemanagementsystem

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val allEmployees: LiveData<List<Employee>> = employeeDao.getAllEmployees()

    @WorkerThread
    suspend fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }

    @WorkerThread
    suspend fun update(employee: Employee) {
        employeeDao.update(employee)
    }

    @WorkerThread
    suspend fun delete(employee: Employee) {
        employeeDao.delete(employee)
    }
}
