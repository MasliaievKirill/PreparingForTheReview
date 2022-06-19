package com.masliaiev.preparingforthereview.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.masliaiev.preparingforthereview.domain.entity.Employee

class EmployeeDiffCallback: DiffUtil.ItemCallback<Employee>() {

    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}