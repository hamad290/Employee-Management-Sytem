package com.example.employeemanagementsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EmployeeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddEmployee: FloatingActionButton = findViewById(R.id.buttonAddEmployee)
        buttonAddEmployee.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        adapter = EmployeeListAdapter(this) { employee ->
            val intent = Intent(this, UpdateEmployeeActivity::class.java)
            intent.putExtra("employee_id", employee.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        employeeViewModel.allEmployees.observe(this, Observer { employees ->
            employees?.let { adapter.setEmployees(it) }
        })
    }
}
