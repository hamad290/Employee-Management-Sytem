package com.example.employeemanagementsystem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class UpdateEmployeeActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var employee: Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        employeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        val employeeId = intent.getIntExtra("employee_id", -1)
        if (employeeId == -1) {
            Toast.makeText(this, "Invalid Employee ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        employeeViewModel.allEmployees.observe(this) { employees ->
            employee = employees.find { it.id == employeeId } ?: return@observe
            findViewById<EditText>(R.id.editTextFirstName).setText(employee.firstName)
            findViewById<EditText>(R.id.editTextLastName).setText(employee.lastName)
            findViewById<EditText>(R.id.editTextEmail).setText(employee.email)
            findViewById<EditText>(R.id.editTextPhone).setText(employee.phone)
            findViewById<EditText>(R.id.editTextAddress).setText(employee.address)
            findViewById<EditText>(R.id.editTextDesignation).setText(employee.designation)
            findViewById<EditText>(R.id.editTextSalary).setText(employee.salary.toString())
        }

        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val firstName = findViewById<EditText>(R.id.editTextFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.editTextLastName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val phone = findViewById<EditText>(R.id.editTextPhone).text.toString()
            val address = findViewById<EditText>(R.id.editTextAddress).text.toString()
            val designation = findViewById<EditText>(R.id.editTextDesignation).text.toString()
            val salary = findViewById<EditText>(R.id.editTextSalary).text.toString().toDouble()

            val updatedEmployee = employee.copy(
                firstName = firstName,
                lastName = lastName,
                email = email,
                phone = phone,
                address = address,
                designation = designation,
                salary = salary
            )
            employeeViewModel.update(updatedEmployee)
            finish()
        }

        val buttonDelete: Button = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Delete Employee")
                setMessage("Are you sure you want to delete this employee?")
                setPositiveButton("Yes") { _, _ ->
                    employeeViewModel.delete(employee)
                    finish()
                }
                setNegativeButton("No", null)
            }.create().show()
        }
    }
}
