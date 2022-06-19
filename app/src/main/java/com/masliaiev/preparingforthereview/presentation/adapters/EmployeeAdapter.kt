package com.masliaiev.preparingforthereview.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.masliaiev.preparingforthereview.databinding.ItemEmployeeBinding
import com.masliaiev.preparingforthereview.domain.entity.Employee

class EmployeeAdapter: ListAdapter<Employee, EmployeeViewHolder>(EmployeeDiffCallback()) {

    var onItemClickListener: ((Employee) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = getItem(position)

        holder.binding.tvEmployeeId.text = employee.id.toString()
        holder.binding.tvEmployeeName.text = employee.firstName

        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(employee)
        }
    }
}