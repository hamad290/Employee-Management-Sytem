package com.example.employeemanagementsystem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EmployeeRepository
    val allEmployees: LiveData<List<Employee>>

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        allEmployees = repository.allEmployees
    }

    fun insert(employee: Employee) = viewModelScope.launch {
        repository.insert(employee)
    }

    fun update(employee: Employee) = viewModelScope.launch {
        repository.update(employee)
    }

    fun delete(employee: Employee) = viewModelScope.launch {
        repository.delete(employee)
    }
}
