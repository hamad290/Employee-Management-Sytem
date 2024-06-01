package com.example.employeemanagementsystem
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val firstName = findViewById<EditText>(R.id.editTextFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.editTextLastName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val phone = findViewById<EditText>(R.id.editTextPhone).text.toString()
            val address = findViewById<EditText>(R.id.editTextAddress).text.toString()
            val designation = findViewById<EditText>(R.id.editTextDesignation).text.toString()
            val salary = findViewById<EditText>(R.id.editTextSalary).text.toString().toDouble()

            val employee = Employee(firstName = firstName, lastName = lastName, email = email, phone = phone, address = address, designation = designation, salary = salary)
            employeeViewModel.insert(employee)
            finish()
        }
    }
}
