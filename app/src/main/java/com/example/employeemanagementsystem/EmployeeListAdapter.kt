package com.example.employeemanagementsystem

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class EmployeeListAdapter internal constructor(
    context: Context,
    private val onEmployeeClick: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var employees = emptyList<Employee>()

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeItemView: TextView = itemView.findViewById(R.id.textView)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onEmployeeClick(employees[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return EmployeeViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val current = employees[position]
        holder.employeeItemView.text = "${current.firstName} ${current.lastName} - ${current.designation}"
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setEmployees(employees: List<Employee>) {
        this.employees = employees
        notifyDataSetChanged()
    }

    override fun getItemCount() = employees.size
}
